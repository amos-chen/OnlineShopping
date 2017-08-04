package com.taotao.portal.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by ${chenlunwei} on 2017/8/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    @Override
    public TbUser getUserByToken(String token) {
        try {
            String result = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            ExecuteJsonResult<TbUser> parseJson = ExecuteJsonResult.parseJson(result, TbUser.class);
            if (parseJson.isSuccess()) {
                return parseJson.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
