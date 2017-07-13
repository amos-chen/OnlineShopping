package com.taotao.portal.controller;

import com.taotao.dto.Item;
import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/7/13.
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}.html")
	public String queryItemById(@PathVariable("itemId") String itemId, Model model){
		Item item = itemService.queryItemById(Long.parseLong(itemId));
		model.addAttribute("item",item);
		return "item";
	}

	@RequestMapping(value = "/item/{itemId}/desc",
			produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String queryItemDescById(@PathVariable("itemId") String itemId){
		TbItemDesc tbItemDesc = itemService.queryItemDescById(Long.parseLong(itemId));
		return tbItemDesc.getItemDesc();
	}


}
