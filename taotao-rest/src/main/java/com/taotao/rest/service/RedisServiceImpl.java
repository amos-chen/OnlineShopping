package com.taotao.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chenlunwei on 2017/7/4.
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Resource(name = "singleton")
	private JedisClient jedisClient;

	@Value("${INDEX_BIGADD_CACHE}")
	private String INDEX_BIGADD_CACHE;

	@Override
	public Long syncContent(String cid) {
		try {
			long result = jedisClient.hdel(INDEX_BIGADD_CACHE, cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
