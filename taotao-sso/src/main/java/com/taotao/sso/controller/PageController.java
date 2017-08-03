package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlunwei on 2017/8/2.
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/showLogin")
	public String showLogin(){
		return "login";
	}

	@RequestMapping("/showRegister")
	public String showRegister(){
		return "register";
	}

}
