package com.taotao.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dto.ExecuteItemsJsonResult;
import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.JSTree;
import com.taotao.exception.DataInsertFailException;
import com.taotao.exception.DataUpdateFailException;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ${chenlunwei} on 2017/6/21.
 */

@Controller
@RequestMapping(value = "/taotao/manager")
public class ContentController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/contentCat", method = RequestMethod.GET)
	@ResponseBody
	public List<JSTree> getJstree() {
		return contentService.queryJstreeNode();
	}


	@RequestMapping(value = "/contentCat/list", method = RequestMethod.GET)
	@ResponseBody
	public List<TbContentCategory> getContentCatList() {
		return contentService.queryContentCat();
	}

	@RequestMapping(value = "/add/contentCat", method = RequestMethod.POST,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public ExecuteJsonResult<List<Integer>> getContentCatList(String[] ParentId, String ContentName) {
		ExecuteJsonResult<List<Integer>> result;
		try {
			List<Integer> data = contentService.addContentCat(ParentId, ContentName);
			result = new ExecuteJsonResult<>(true, data);
			return result;
		} catch (DataInsertFailException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		} catch (TaotaoException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/get/{id}/contentCat", method = RequestMethod.GET,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public ExecuteJsonResult<TbContentCategory> queryContentCat(@PathVariable("id") String id) {
		ExecuteJsonResult<TbContentCategory> result;
		try {
			TbContentCategory data = contentService.queryContentCat(Long.parseLong(id));
			result = new ExecuteJsonResult<>(true, data);
			return result;
		} catch (DataInsertFailException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		} catch (TaotaoException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/update/{id}/contentCat", method = RequestMethod.POST,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public ExecuteJsonResult<Integer> updateContentCat(@PathVariable("id") String id, String ContentName) {
		ExecuteJsonResult<Integer> result;
		try {
			int data = contentService.updateContentCat(id, ContentName);
			result = new ExecuteJsonResult<>(true, data);
			return result;
		} catch (DataUpdateFailException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		} catch (TaotaoException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/delete/{id}/contentCat", method = RequestMethod.POST,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public ExecuteJsonResult<Integer> deleteContentCat(@PathVariable("id") String id) {
		ExecuteJsonResult<Integer> result;
		try {
			int data = contentService.deleteContentCat(id);
			result = new ExecuteJsonResult<>(true, data);
			return result;
		} catch (DataUpdateFailException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		} catch (TaotaoException e) {
			result = new ExecuteJsonResult<>(false, e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/content/{id}/list", method = RequestMethod.GET)
	@ResponseBody
	public ExecuteItemsJsonResult<List<TbContent>> queryList(@PathVariable String id, Integer offset, Integer limit, String search, String sort,
															 String order) {
		ExecuteItemsJsonResult<List<TbContent>> result;
		try {
			PageHelper.offsetPage(offset, limit);
			List<TbContent> tbContents = contentService.queryContentList(id);
			PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContents);
			long total = tbContentPageInfo.getTotal();
			result = new ExecuteItemsJsonResult<>(true, total, tbContents);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ExecuteItemsJsonResult<>(false, e.getMessage());
		}
		return result;
	}


}
