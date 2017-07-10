package com.taotao.portal.service;

import com.taotao.dto.SearchResult;

/**
 * Created by chenlunwei on 2017/7/9.
 */
public interface SearchService {

	SearchResult query(String query, String page);
}
