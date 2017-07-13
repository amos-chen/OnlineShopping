package com.taotao.rest.controller;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/7/12.
 */

@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;


	@RequestMapping("/item/{itemId}/base")
	@ResponseBody
	public ExecuteJsonResult<TbItem> queryTbItemById(@PathVariable("itemId") String itemId){
		ExecuteJsonResult<TbItem> result;
		try{
			TbItem tbItem = tbItemService.queryTbItem(Long.parseLong(itemId));
			result = new ExecuteJsonResult<>(true,tbItem);
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<>(false,e.getMessage());
			return result;
		}
	}

	@RequestMapping("/item/{itemId}/desc")
	@ResponseBody
	public ExecuteJsonResult<TbItemDesc> queryTbItemDescById(@PathVariable("itemId") String itemId){
		ExecuteJsonResult<TbItemDesc> result;
		try{
			TbItemDesc tbItemDesc = tbItemService.queryTbItemDesc(Long.parseLong(itemId));
			result = new ExecuteJsonResult<>(true,tbItemDesc);
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<>(false,e.getMessage());
			return result;
		}
	}

	@RequestMapping("/item/{itemId}/param")
	@ResponseBody
	public ExecuteJsonResult<TbItemParamItem> queryTbItemParamById(@PathVariable("itemId") String itemId){
		ExecuteJsonResult<TbItemParamItem> result;
		try{
			TbItemParamItem tbItemParamItem = tbItemService.queryTbItemParam(Long.parseLong(itemId));
			result = new ExecuteJsonResult<>(true,tbItemParamItem);
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<>(false,e.getMessage());
			return result;
		}
	}


}
