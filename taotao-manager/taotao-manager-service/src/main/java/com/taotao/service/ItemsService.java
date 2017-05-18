package com.taotao.service;

import com.taotao.dto.JSTree;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;

import java.util.List;

/**
 * Created by chenlunwei on 2017/4/30.
 */
public interface ItemsService {

	TbItem queryById(long id);

	List<TbItem> queryList(String search);

	List<JSTree> queryJSTrees(String id);

	int insertItem(TbItem tbItem,String description);

	List<Integer> deleteItem(String[] itemIdList);

	TbItemCat queryItemCat(Long cid);

	TbItemParam queryItemParam(Long cid);

	int insertTbitemParam(TbItemParam tbItemParam);

	int updateTbitemParam(TbItemParam tbItemParam);
}
