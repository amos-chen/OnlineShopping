package com.taotao.web;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/taotao")
public class ItemController {
	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = "/{itemId}/item",method = RequestMethod.GET,
						produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public TbItem queryItemById(@PathVariable("itemId")long itemId){
		TbItem tbItem = itemsService.queryById(itemId);
		return tbItem;
	}
}
