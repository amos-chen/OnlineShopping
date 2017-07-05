package com.taotao.rest.controller;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.exception.DataNotFindException;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/7/4.
 */
@Controller
public class RedisController {
	@Autowired
	private RedisService redisService;

	@RequestMapping(value = "/sync/content/{cid}",method = RequestMethod.GET)
	@ResponseBody
	public ExecuteJsonResult<Long> syncContentCat(@PathVariable String cid){
		ExecuteJsonResult<Long> result;
		try {
			Long data = redisService.syncContent(cid);
			if(data==0l||data==null){
				throw new DataNotFindException("未找到相应数据");
			}
			result = new ExecuteJsonResult<Long>(true,data);
			return result;
		}catch (Exception e){
			return new ExecuteJsonResult<Long>(false,"系统内容错误");
		}
	}
}
