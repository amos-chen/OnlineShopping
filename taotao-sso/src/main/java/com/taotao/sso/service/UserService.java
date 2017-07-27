package com.taotao.sso.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;

/**
 * Created by ${chenlunwei} on 2017/7/26.
 */
public interface UserService {

    TbUser checkData(String data, Integer type);

}
