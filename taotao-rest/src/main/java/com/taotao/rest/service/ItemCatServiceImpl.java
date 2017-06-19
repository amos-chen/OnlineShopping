package com.taotao.rest.service;

import com.taotao.dao.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlunwei on 2017/6/18.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;


	@Override
	public CatResult getCatResult() {
		CatResult catResult = new CatResult();
		long id = 0;
		catResult.setData(getItem(id));
		return catResult;
	}

	public List<?> getItem(Long id) {
		List<TbItemCat> tbItemCats = tbItemCatMapper.queryListByParentId(id);
		List itemList = new ArrayList<>();
		int count = 0;
		for (TbItemCat tbItemCat : tbItemCats) {
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if(tbItemCat.getParentId()==0){
					if(count==14){
						break;
					}
					count++;
					catNode.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				}else{
					catNode.setName(tbItemCat.getName());
				}
				String url = "/products/" + tbItemCat.getId() + ".html";
				catNode.setUrl(url);
				catNode.setItem(getItem(tbItemCat.getId()));
				itemList.add(catNode);
			} else {
				String name = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
				itemList.add(name);
			}
		}
		return itemList;
	}
}
