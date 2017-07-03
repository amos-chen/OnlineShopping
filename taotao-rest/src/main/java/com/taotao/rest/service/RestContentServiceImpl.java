package com.taotao.rest.service;

import com.taotao.dao.TbContentMapper;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenlunwei on 2017/7/1.
 */
@Service
public class RestContentServiceImpl implements RestContentService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private TbContentMapper tbContentMapper;


	@Override
	public List<TbContent> queryContentList(String cid) throws TaotaoException{

		try{
			List<TbContent> contentList = tbContentMapper.queryListByCid(Long.parseLong(cid));
			return contentList;
		}catch (TaotaoException e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}
	}
}
