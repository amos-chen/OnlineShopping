package com.taotao.rest.controller;

import com.taotao.pojo.TbContent;
import com.taotao.rest.service.RestContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by chenlunwei on 2017/7/1.
 */
@Controller
public class ContentController {

	@Autowired
	private RestContentService restContentService;

	@RequestMapping(value = "/{cid}/content/list", method = RequestMethod.GET)
	@ResponseBody
	public List<TbContent> queryContentList(@PathVariable("cid") String cid) {
		List<TbContent> result = restContentService.queryContentList(cid);
		return result;
	}
}
