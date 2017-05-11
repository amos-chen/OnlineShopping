package com.taotao.service;

import com.taotao.dao.TbItemCatMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dto.JSTree;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public TbItem queryById(@Param("id") long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbItem> queryList(String search) {
		return tbItemMapper.queryList(search);
	}

	@Override
	public List<JSTree> queryJSTrees(String id) {	//根据id查询所有子类
													//因为jstree初始化之后会发起一个get请求，id参数为‘#’
		if (id == null || "".equals(id.trim()) || "#".equals(id)) {
			id="0";
		}
		//把String参数转成int型
		int parentId = Integer.parseInt(id);
		//根据id查询此Id所对应的所有子类
		List<TbItemCat> tbItemCats = tbItemCatMapper.queryListByParentId(parentId);
		//把子类信息转成JStree所需要的POJO
		List<JSTree> jsTrees = new ArrayList<>();
		for (TbItemCat ItemCat : tbItemCats) {
			boolean state = ItemCat.getIsParent();
			JSTree jsTree;
			if (state) {
				jsTree = new JSTree(ItemCat.getId().toString(), ItemCat.getName(),
						"fa fa-folder fw", state);
			} else {
				jsTree = new JSTree(ItemCat.getId().toString(),ItemCat.getName(),
						"fa fa-file fw", state);
			}
			jsTrees.add(jsTree);
		}
		return jsTrees;

	}
}