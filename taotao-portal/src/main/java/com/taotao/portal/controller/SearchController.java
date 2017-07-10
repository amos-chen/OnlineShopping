package com.taotao.portal.controller;

import com.taotao.dto.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenlunwei on 2017/7/10.
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public String search(@RequestParam("q") String query,@RequestParam(defaultValue = "1")String page, Model model){
		SearchResult result = searchService.query(query, page);
		model.addAttribute("query",query);
		model.addAttribute("totalPages",result.getPageCount());
		model.addAttribute("itemList",result.getItems());
		model.addAttribute("page",page);
		return "search";
	}
}
