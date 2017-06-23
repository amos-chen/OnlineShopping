package com.taotao.service;

import com.taotao.dto.JSTree;
import com.taotao.dto.JSTreeAjax;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;

import java.util.List;

/**
 * Created by chenlunwei on 2017/4/30.
 */
public interface ItemsService {

	TbItem queryById(long id);

	//根据条件查询商品
	List<TbItem> queryList(String search);

	//根据id查询所有子类
	List<JSTreeAjax> queryJSTrees(String id);

	int insertItem(TbItem tbItem,String description, String itemParameter);

	List<Integer> deleteItem(String[] itemIdList);

	TbItemCat queryItemCat(Long cid);

	TbItemParam queryItemParam(Long cid);

	int insertTbitemParam(TbItemParam tbItemParam);

	int updateTbitemParam(TbItemParam tbItemParam);
}
