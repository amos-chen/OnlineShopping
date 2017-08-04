package com.taotao.portal.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;

/**
 * Created by ${chenlunwei} on 2017/8/4.
 */
public interface UserService {

    TbUser getUserByToken(String token);
}
