package com.taotao.service;

import com.taotao.dto.JSTree;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;

import java.util.List;

/**
 * Created by ${chenlunwei} on 2017/6/21.
 */
public interface ContentService {

    List<JSTree> queryJstreeNode();

    List<TbContentCategory> queryContentCat();

    List<Integer> addContentCat(String[] ParentId,String ContentName);

    TbContentCategory queryContentCat(Long id);

    int updateContentCat(String id,String name);

    List<Integer> deleteContentCat(String id);

    List<TbContent> queryContentList(String categoryId);

    TbContent queryContentById(String id);

    int addContent(String cid,TbContent tbContent);

    int updateContent(TbContent tbContent);

    List<Integer> deleteContent(String[] contentIdList,String cid);

}
