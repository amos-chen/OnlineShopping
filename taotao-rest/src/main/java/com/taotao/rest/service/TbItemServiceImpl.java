package com.taotao.rest.service;

import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.utils.ProtostuffUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chenlunwei on 2017/7/12.
 */
@Service
public class TbItemServiceImpl implements TbItemService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Resource(name = "singleton")
	private JedisClient jedisClient;

	@Value("${REDIS_TBITEM}")
	private String REDIS_TBITEM;

	@Value("${REDIS_TBITEM_EXPIRETIME}")
	private Integer REDIS_TBITEM_EXPIRETIME;


	@Override
	public TbItem queryTbItem(Long id) throws TaotaoException{

		//先从redis中取TbItem的数据
		try {
			byte[] data = jedisClient.get((REDIS_TBITEM + ":" + id + ":base").getBytes());
			if (data != null && data.length > 0) {
				TbItem tbItem = ProtostuffUtil.deserializer(data, TbItem.class);
				return tbItem;
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}

		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);

		//往redis中储存TbItem数据
		try {
			byte[] data = ProtostuffUtil.serializer(tbItem);
			jedisClient.setex((REDIS_TBITEM + ":" + id + ":base").getBytes(),
					REDIS_TBITEM_EXPIRETIME,data);
		}catch (Exception e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}
		return tbItem;
	}

	@Override
	public TbItemDesc queryTbItemDesc(Long id) throws TaotaoException{
		//先从redis中取tbItemDesc的数据
		try {
			byte[] data = jedisClient.get((REDIS_TBITEM + ":" + id + ":desc").getBytes());
			if (data != null && data.length > 0) {
				TbItemDesc tbItemDesc = ProtostuffUtil.deserializer(data, TbItemDesc.class);
				return tbItemDesc;
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}

		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);

		//往redis中储存tbItemDesc数据
		try {
			byte[] data = ProtostuffUtil.serializer(tbItemDesc);
			jedisClient.setex((REDIS_TBITEM + ":" + id + ":desc").getBytes(),
					REDIS_TBITEM_EXPIRETIME,data);
		}catch (Exception e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}
		return tbItemDesc;
	}

	@Override
	public TbItemParamItem queryTbItemParam(Long itemId) throws TaotaoException{
		//先从redis中取tbItemDesc的数据
		try {
			byte[] data = jedisClient.get((REDIS_TBITEM + ":" + itemId + ":param").getBytes());
			if (data != null && data.length > 0) {
				TbItemParamItem tbItemParamItem = ProtostuffUtil.deserializer(data, TbItemParamItem.class);
				return tbItemParamItem;
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}

		TbItemParamItem tbItemParamItem = tbItemParamItemMapper.selectByItemId(itemId);

		//往redis中储存tbItemDesc数据
		try {
			byte[] data = ProtostuffUtil.serializer(tbItemParamItem);
			jedisClient.setex((REDIS_TBITEM + ":" + itemId + ":param").getBytes(),
					REDIS_TBITEM_EXPIRETIME,data);
		}catch (Exception e){
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}
		return tbItemParamItem;
	}
}
