package com.taotao.portal.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by chenlunwei on 2017/7/13.
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${ITEM_INFO_BASEURL}")
	private String ITEM_INFO_BASEURL;

	@Override
	public Item queryItemById(Long itemId) {
		String jsonString = HttpClientUtil.doGet(ITEM_INFO_BASEURL+"/"+itemId+"/base");
		ExecuteJsonResult<TbItem> result = ExecuteJsonResult.parseJson(jsonString,TbItem.class);
		Item item = null;
		if(result.isSuccess()){
			item = new Item();
			item.setId(result.getData().getId().toString());
			item.setImage(result.getData().getImage());
			item.setPrice(result.getData().getPrice());
			item.setTitle(result.getData().getTitle());
			item.setSellPoint(result.getData().getSellPoint());
		}
		return item;
	}

	@Override
	public TbItemDesc queryItemDescById(Long itemId) {
		String jsonString = HttpClientUtil.doGet(ITEM_INFO_BASEURL+"/"+itemId+"/desc");
		ExecuteJsonResult<TbItemDesc> result = ExecuteJsonResult.parseJson(jsonString,TbItemDesc.class);
		return result.getData();
	}

	@Override
	public TbItemParamItem queryItemParamById(Long itemId) {
		String jsonString = HttpClientUtil.doGet(ITEM_INFO_BASEURL+"/"+itemId+"/param");
		ExecuteJsonResult<TbItemParamItem> result = ExecuteJsonResult.parseJson(jsonString,TbItemParamItem.class);
		return result.getData();
	}
}
