package com.taotao.dao;

import com.taotao.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insertWithoutId(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    TbContentCategory selectByPrimaryKey(Long id);

    TbContentCategory selectByName(String name);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);

    List<TbContentCategory> queryByParentId(Long parentId);

    List<TbContentCategory> queryList();

    Long queryMaxId();

}