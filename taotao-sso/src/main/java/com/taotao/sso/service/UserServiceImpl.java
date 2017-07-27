package com.taotao.sso.service;

import com.taotao.dao.TbUserMapper;
import com.taotao.dto.ExecuteJsonResult;
import com.taotao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ${chenlunwei} on 2017/7/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser checkData(String data, Integer type) {
        if(1==type){
            return tbUserMapper.selectByUsername(data);
        }else if(2==type){
            return tbUserMapper.selectByPhoneNumber(data);
        }else{
            return tbUserMapper.selectByEmail(data);
        }
    }
}
