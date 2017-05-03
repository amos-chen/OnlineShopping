package com.taotao.service;

import com.taotao.dao.TbItemMapper;
import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem queryById(@Param("id") long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}
}
