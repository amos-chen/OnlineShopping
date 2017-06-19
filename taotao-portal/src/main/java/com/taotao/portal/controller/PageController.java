package com.taotao.portal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlunwei on 2017/6/18.
 */
@Controller
public class PageController {

	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
}
