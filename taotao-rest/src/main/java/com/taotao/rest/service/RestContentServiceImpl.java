package com.taotao.rest.service;

import com.taotao.dao.TbContentMapper;
import com.taotao.exception.TaotaoException;
import com.taotao.pojo.TbContent;
import com.taotao.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenlunwei on 2017/7/1.
 */
@Service
public class RestContentServiceImpl implements RestContentService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "singleton")
	private JedisClient jedisClient;

	@Autowired
	private TbContentMapper tbContentMapper;
	@Value("${INDEX_BIGADD_CACHE}")
	private String INDEX_BIGADD_CACHE;

	@Override
	public List<TbContent> queryContentList(String cid) throws TaotaoException {
		//从redis中取数据，如果有数据则直接返回，如果没有数据则查找数据库，并往redis中存相应数据
		try {
			String cacheString = jedisClient.hget(INDEX_BIGADD_CACHE,cid);
			if(cacheString!=null&&cacheString!=""){
				List<TbContent> contentList = JsonUtils.jsonToList(cacheString,TbContent.class);
				return contentList;
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		List<TbContent> contentList = tbContentMapper.queryListByCid(Long.parseLong(cid));
		//把数组存到redis中
		try {
			String cacheString = JsonUtils.objectToJson(contentList);
			jedisClient.hset(INDEX_BIGADD_CACHE,cid,cacheString);
		}catch (Exception e){
			e.printStackTrace();
		}
		return contentList;
	}
}
