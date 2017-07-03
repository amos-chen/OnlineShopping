package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by chenlunwei on 2017/7/1.
 */
public interface RestContentService {

	List<TbContent> queryContentList(String cid);

}
