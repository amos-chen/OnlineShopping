package com.taotao.sso.controller;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/8/2.
 */

@RequestMapping(value = "/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/check/{content}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String content, @PathVariable Integer type, String callback) {
		ExecuteJsonResult result = null;
		if (StringUtils.isBlank(content)) {
			result = new ExecuteJsonResult(false, "校验内容不能为空！ ");
		}
		if (type == null) {
			result = new ExecuteJsonResult(false, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = new ExecuteJsonResult(false, "数据内容类型错误！");
		}

		//校验出错
		if (result != null) {
			if (callback != null) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}
		}

		try {
			TbUser tbUser = userService.checkData(content, type);
			if (tbUser != null) {
				result = new ExecuteJsonResult(true, false);
			} else {
				result = new ExecuteJsonResult(true, true);
			}
		} catch (Exception e) {
			result = new ExecuteJsonResult(false, "校验失败！");
		}
		if (callback != null) {
			MappingJacksonValue value = new MappingJacksonValue(result);
			value.setJsonpFunction(callback);
			return value;
		} else {
			return result;
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult signUp(TbUser tbUser) {
		try {
			ExecuteJsonResult result = userService.Signup(tbUser);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ExecuteJsonResult(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult login(String username, String password) {
		try {
			ExecuteJsonResult result = userService.login(username, password);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ExecuteJsonResult(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		ExecuteJsonResult result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ExecuteJsonResult(false, e.getMessage());
		}
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

	@RequestMapping(value = "/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable String token, String callback) {
		ExecuteJsonResult result = null;
		try {
			result = userService.logout(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ExecuteJsonResult(false, e.getMessage());
		}
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

}
