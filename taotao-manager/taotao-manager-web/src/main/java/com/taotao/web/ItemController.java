package com.taotao.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dto.ExecuteItemsJsonResult;
import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.JSTree;
import com.taotao.dto.JSTreeAjax;
import com.taotao.exception.DataInsertFailException;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/taotao/manager")
public class ItemController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = "/{itemId}/item", method = RequestMethod.GET,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public TbItem queryItemById(@PathVariable("itemId") long itemId) {
		TbItem tbItem = itemsService.queryById(itemId);
		return tbItem;
	}

	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	@ResponseBody
	public ExecuteItemsJsonResult<List<TbItem>> queryList(Integer offset, Integer limit, String search, String sort,
														  String order) {
		ExecuteItemsJsonResult<List<TbItem>> result;
		try {
			PageHelper.offsetPage(offset, limit);
			List<TbItem> tbItemList = itemsService.queryList(search);
			PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItemList);
			long total = tbItemPageInfo.getTotal();
			result = new ExecuteItemsJsonResult<List<TbItem>>(true, total, tbItemList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new ExecuteItemsJsonResult<List<TbItem>>(false, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/itemCat", method = RequestMethod.GET)
	@ResponseBody
	public List<JSTreeAjax> queryJSTrees(String id) {
		return itemsService.queryJSTrees(id);
	}


	@RequestMapping(value = "/add/item", method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult<Integer> addItem(TbItem tbItem, String description,String itemParameter) {
		ExecuteJsonResult<Integer> result;
		try {
			int data = itemsService.insertItem(tbItem, description,itemParameter);
			result = new ExecuteJsonResult<Integer>(true, data);
			return result;
		} catch (DataInsertFailException e) {
			result = new ExecuteJsonResult<Integer>(false, "商品信息插入失败！");
			return result;
		} catch (TaotaoException e) {
			result = new ExecuteJsonResult<Integer>(false, "商品信息插入失败！");
			return result;
		} catch (Exception e) {
			result = new ExecuteJsonResult<Integer>(false, "系统内部异常！");
			return result;
		}
	}

	@RequestMapping(value = "/delete/item",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult<List<Integer>> deleteItem(String[] itemIdList){
		ExecuteJsonResult<List<Integer>> result;
		try {
			List<Integer> data = itemsService.deleteItem(itemIdList);
			result = new ExecuteJsonResult<List<Integer>>(true,data);
			return result;
		}catch (DataInsertFailException e){
			result = new ExecuteJsonResult<List<Integer>>(false,e.getMessage());
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<List<Integer>>(false,e.getMessage());
			return result;
		}
	}


	@RequestMapping(value = "/{cid}/itemCat",method = RequestMethod.GET)
	@ResponseBody
	public ExecuteJsonResult<TbItemCat> queryItemCat(@PathVariable("cid") long cid){
		ExecuteJsonResult<TbItemCat> result;
		try{
			TbItemCat tbItemCat = itemsService.queryItemCat(cid);
			result = new ExecuteJsonResult<TbItemCat>(true,tbItemCat);
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<TbItemCat>(false,e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/{cid}/itemParam",method = RequestMethod.GET)
	@ResponseBody
	public ExecuteJsonResult<TbItemParam> queryItemParam(@PathVariable("cid") long cid){
		ExecuteJsonResult<TbItemParam> result;
		try{
			TbItemParam tbItemParam = itemsService.queryItemParam(cid);
			result = new ExecuteJsonResult<TbItemParam>(true,tbItemParam);
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<TbItemParam>(false,e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/insert/{cid}/itemParam",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult<Integer> insertItemParam(@PathVariable("cid") long cid,String paramData){
		ExecuteJsonResult<Integer> result;
		try {
			TbItemParam tbItemParam = new TbItemParam();
			tbItemParam.setItemCatId(cid);
			tbItemParam.setParamData(paramData);
			Date date = new Date();
			tbItemParam.setCreated(date);
			tbItemParam.setUpdated(date);
			Integer data = itemsService.insertTbitemParam(tbItemParam);
			result = new ExecuteJsonResult<Integer>(true,data);
			return result;
		}catch (DataInsertFailException e){
			result = new ExecuteJsonResult<Integer>(false,e.getMessage());
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<Integer>(false,e.getMessage());
			return result;
		}

	}

	@RequestMapping(value = "/update/{cid}/itemParam",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult<Integer> updateItemParam(@PathVariable("cid") long cid,String paramData){
		ExecuteJsonResult<Integer> result;
		try {
			TbItemParam tbItemParam = new TbItemParam();
			tbItemParam.setItemCatId(cid);
			tbItemParam.setParamData(paramData);
			Date date = new Date();
			tbItemParam.setUpdated(date);
			Integer data = itemsService.updateTbitemParam(tbItemParam);
			result = new ExecuteJsonResult<Integer>(true,data);
			return result;
		}catch (DataInsertFailException e){
			result = new ExecuteJsonResult<Integer>(false,e.getMessage());
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<Integer>(false,e.getMessage());
			return result;
		}
	}

}
