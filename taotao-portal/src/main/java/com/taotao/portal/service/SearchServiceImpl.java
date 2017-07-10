package com.taotao.portal.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.SearchResult;
import com.taotao.exception.DataNotFindException;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlunwei on 2017/7/9.
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_URL}")
	private String SEARCH_URL;

	@Override
	public SearchResult query(String query, String page) throws DataNotFindException{
		Map<String, String> params = new HashMap<>();
		params.put("q", query);
		params.put("page", page);
		String JsonString = HttpClientUtil.doGet(SEARCH_URL, params);
		ExecuteJsonResult<SearchResult> result = ExecuteJsonResult.parseJson(JsonString, SearchResult.class);
		if (result.isSuccess()) {
			return result.getData();
		}else {
			throw new DataNotFindException("未查询到相关数据");
		}
	}
}
