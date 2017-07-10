package com.taotao.search.controller;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.search.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenlunwei on 2017/7/9.
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/importall",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult importAllItemToSolr(){
		ExecuteJsonResult result;
		try {
			long time = itemService.addDocument();
			return new ExecuteJsonResult(true,time);
		}catch (Exception e){
			return new ExecuteJsonResult(false,"索引库添加失败");
		}
	}

	@RequestMapping(value = "/{itemId}/import",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult addAndUpdateItemToSolr(@PathVariable("itemId")String itemId){
		ExecuteJsonResult result;
		try {
			long time = itemService.addAndUpdateDocument(itemId);
			return new ExecuteJsonResult(true,time);
		}catch (Exception e){
			return new ExecuteJsonResult(false,"索引库更新失败");
		}
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult deleteItemFromSolr(@RequestParam("itemList")String itemIdList){
		ExecuteJsonResult result;
		try {
			long time = itemService.deleteDocument(itemIdList);
			return new ExecuteJsonResult(true,time);
		}catch (Exception e){
			logger.error(e.getMessage());
			return new ExecuteJsonResult(false,"索引库删除失败");
		}
	}

}
