package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * Created by chenlunwei on 2017/7/12.
 */
public interface TbItemService {

	TbItem queryTbItem(Long id);

	TbItemDesc queryTbItemDesc(Long id);

	TbItemParamItem queryTbItemParam(Long cid);


}
