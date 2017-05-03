package com.taotao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/taotao")
public class PageController {
	@RequestMapping(value = "/manager/list")
	public String showIndex(){
		return "index";
	}
}
