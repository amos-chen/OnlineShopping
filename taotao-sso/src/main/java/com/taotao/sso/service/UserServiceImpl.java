package com.taotao.sso.service;

import com.taotao.dao.TbUserMapper;
import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ${chenlunwei} on 2017/7/26.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Resource(name = "singleton")
	private JedisClient jedisClient;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Value("${USER_SESSION_EXPIRE_TIME}")
	private Integer USER_SESSION_EXPIRE_TIME;

	@Override
	public TbUser checkData(String data, Integer type) {
		if (1 == type) {
			return tbUserMapper.selectByUsername(data);
		} else if (2 == type) {
			return tbUserMapper.selectByPhoneNumber(data);
		} else {
			return tbUserMapper.selectByEmail(data);
		}
	}

	@Override
	public ExecuteJsonResult<Integer> Signup(TbUser tbUser) {
		Date date = new Date();
		tbUser.setCreated(date);
		tbUser.setUpdated(date);
		String md5Pwd = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
		tbUser.setPassword(md5Pwd);
		int result = tbUserMapper.insertSelective(tbUser);
		return new ExecuteJsonResult(true, result);
	}

	@Override
	public ExecuteJsonResult<String> login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		TbUser user = tbUserMapper.selectByUsername(username);
		if (user == null) {
			return new ExecuteJsonResult<String>(false,"用户名或密码错误",null);
		}
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return new ExecuteJsonResult<String>(false,"用户名或密码错误",null);
		}
		String token = UUID.randomUUID().toString();
		user.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, USER_SESSION_EXPIRE_TIME);

		//设置cookie
		CookieUtils.setCookie(request,response,"TT_TOKEN",token);

		return new ExecuteJsonResult<String>(true,null,token);
	}

	@Override
	public ExecuteJsonResult<TbUser> getUserByToken(String token) {
		String result = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		if(StringUtils.isBlank(result)){
			return new ExecuteJsonResult<TbUser>(false,"用户登陆已过期，请重新登陆！");
		}
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, USER_SESSION_EXPIRE_TIME);
		return new ExecuteJsonResult<TbUser>(true,JsonUtils.jsonToPojo(result,TbUser.class));
	}

	@Override
	public ExecuteJsonResult logout(String token) {
		Long result = jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		if(result==null||result==0){
			return new ExecuteJsonResult(false,"退出登陆失败，请重试！");
		}
		return new ExecuteJsonResult(true,result);
	}

}
