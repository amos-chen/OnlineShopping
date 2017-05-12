package com.taotao.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.JSTree;
import com.taotao.pojo.TbItem;

import java.util.List;

/**
 * Created by chenlunwei on 2017/4/30.
 */
public interface ItemsService {

	TbItem queryById(long id);

	List<TbItem> queryList(String search);

	List<JSTree> queryJSTrees(String id);

	int insertItem(TbItem tbItem,String description);
}
