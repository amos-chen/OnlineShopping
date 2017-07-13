package com.taotao.portal.service;

import com.taotao.dto.Item;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * Created by chenlunwei on 2017/7/13.
 */
public interface ItemService {

	Item queryItemById(Long itemId);

	TbItemDesc queryItemDescById(Long itemId);

	TbItemParamItem queryItemParamById(Long itemId);

}
