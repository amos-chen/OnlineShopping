package com.taotao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/taotao/manager")
public class PageController {
	@RequestMapping(value = "/")
	public String showIndex(){
		return "index";
	}

	@RequestMapping(value = "/{page}")
	public String getPage(@PathVariable("page")String page){
		return page;
	}
}
