package com.taotao.search.controller;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/7/9.
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/importall")
	@ResponseBody
	public ExecuteJsonResult addDocument(){
		ExecuteJsonResult result;
		try {
			int time = itemService.addDocument();
			return new ExecuteJsonResult(true,time);
		}catch (Exception e){
			return new ExecuteJsonResult(false,"系统内部错误");
		}
	}

}
