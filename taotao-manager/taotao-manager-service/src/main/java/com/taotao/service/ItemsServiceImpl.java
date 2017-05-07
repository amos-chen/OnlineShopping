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
	public List<JSTree> queryJSTrees(String id) {

		if (id == null || "".equals(id.trim()) || "#".equals(id)) {
			id = "0";
		}
		int parentId = Integer.parseInt(id);
		List<TbItemCat> tbItemCats = tbItemCatMapper.queryListByParentId(parentId);
		List<JSTree> jsTrees = new ArrayList<>();
		for (TbItemCat tbItemCat : tbItemCats) {
			boolean state = tbItemCat.getIsParent();
			JSTree jsTree;
			if (state) {
				jsTree = new JSTree(tbItemCat.getId().toString(), tbItemCat.getName(),
						"fa fa-folder fw", state);
			} else {
				jsTree = new JSTree(tbItemCat.getId().toString(), tbItemCat.getName(),
						"fa fa-file fw", state);
			}
			jsTrees.add(jsTree);
		}
		return jsTrees;

	}
}