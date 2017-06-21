package com.taotao.service;

import com.taotao.dto.JSTree;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;

import java.util.List;

/**
 * Created by ${chenlunwei} on 2017/6/21.
 */
public interface ContentService {
    List<TbContent> queryContent();

    List<JSTree> queryJstreeNode(String id);

    List<TbContentCategory> queryContentCat();

}
