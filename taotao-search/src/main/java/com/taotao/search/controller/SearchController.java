package com.taotao.search.controller;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenlunwei on 2017/7/9.
 */

@Controller
public class SearchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/query", method = RequestMethod.GET,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public ExecuteJsonResult<SearchResult> search(@RequestParam("q")String q,
												  @RequestParam(defaultValue="1")Integer page,
												  @RequestParam(defaultValue="20")Integer rows) {
		ExecuteJsonResult<SearchResult> result;
		try {
			if (StringUtils.isBlank(q)){
				return new ExecuteJsonResult<>(false, "搜索内容不能为空");
			}
			SearchResult data = searchService.search(q,page,rows);
			result = new ExecuteJsonResult<>(true,data);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ExecuteJsonResult<>(false, e.getMessage());
		}
	}

}
