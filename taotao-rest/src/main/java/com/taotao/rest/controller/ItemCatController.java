package com.taotao.rest.controller;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/6/18.
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;


	//两种实现方法
	/*@RequestMapping(value = "/itemcat/list",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String getCatResult(String callback){
		CatResult catResult = itemCatService.getCatResult();
		String jsonData = JsonUtils.objectToJson(catResult);
		String result = callback+"("+jsonData+");";
		return result;
	}*/

	@RequestMapping(value = "/itemcat/list",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object getCatResult(String callback){
		CatResult catResult = itemCatService.getCatResult();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
