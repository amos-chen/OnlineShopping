package com.taotao.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dto.ExecuteItemsJsonResult;
import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.JSTree;
import com.taotao.exception.DataInsertFailException;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemsService;
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
 * Created by chenlunwei on 2017/4/30.
 */
@Controller
@RequestMapping(value = "/taotao/manager")
public class ItemController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = "/{itemId}/item",method = RequestMethod.GET,
						produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public TbItem queryItemById(@PathVariable("itemId")long itemId){
		TbItem tbItem = itemsService.queryById(itemId);
		return tbItem;
	}

	@RequestMapping(value = "/item/list",method = RequestMethod.GET)
	@ResponseBody
	public ExecuteItemsJsonResult<List<TbItem>> queryList(int offset, int limit, String search, String sort,
														  String order){
		ExecuteItemsJsonResult<List<TbItem>> result;
//		System.out.println(search);
		try{
			PageHelper.offsetPage(offset,limit);
			List<TbItem> tbItemList = itemsService.queryList(search);
			PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItemList);
			long total = tbItemPageInfo.getTotal();
			result = new ExecuteItemsJsonResult<List<TbItem>>(true,total,tbItemList);
		} catch (Exception e){
			logger.error(e.getMessage(),e);
			result = new ExecuteItemsJsonResult<List<TbItem>>(false, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/itemCat",method = RequestMethod.GET)
	@ResponseBody
	public List<JSTree> queryJSTrees(String id){
		return itemsService.queryJSTrees(id);
	}


	@RequestMapping(value = "/add/item",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult<Integer> addItem(TbItem tbItem,String description){
		ExecuteJsonResult<Integer> result;
		try {
			int data = itemsService.insertItem(tbItem,description);
			result = new ExecuteJsonResult<Integer>(true,data);
			return result;
		}catch (DataInsertFailException e){
			result = new ExecuteJsonResult<Integer>(false,"商品信息插入失败！");
			return result;
		}catch (TaotaoException e){
			result = new ExecuteJsonResult<Integer>(false,"商品信息插入失败！");
			return result;
		}catch (Exception e){
			result = new ExecuteJsonResult<Integer>(false,"系统内部异常！");
			return result;
		}
	}
}
