package com.taotao.portal.controller;


import com.taotao.portal.service.PortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlunwei on 2017/6/18.
 */
@Controller
public class PageController {

	@Autowired
	private PortalContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model){
		String bigAdJson = contentService.getContentList();
		model.addAttribute("ad1",bigAdJson);
		return "index";
	}

}
