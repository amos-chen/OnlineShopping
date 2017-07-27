package com.taotao.dao;

import com.taotao.pojo.TbUser;

public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    //根据用户名查询用户
    TbUser selectByUsername(String username);

    //根据手机号码查询用户
    TbUser selectByPhoneNumber(String phoneNumber);

    //根据Email查询用户
    TbUser selectByEmail(String email);

    int updateByPrimaryKey(TbUser record);
}