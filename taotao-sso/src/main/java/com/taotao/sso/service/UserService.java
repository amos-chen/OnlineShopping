package com.taotao.sso.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;

/**
 * Created by ${chenlunwei} on 2017/7/26.
 */
public interface UserService {

    //校验注册时的信息是否可用
    TbUser checkData(String data, Integer type);

    //注册service
    ExecuteJsonResult<Integer> Signup(TbUser tbUser);

    //登陆service
    ExecuteJsonResult<String> login(String username,String password);

    //根据token获取用户信息
    ExecuteJsonResult<TbUser> getUserByToken(String token);

    //退出登陆service
    ExecuteJsonResult logout(String token);

}
