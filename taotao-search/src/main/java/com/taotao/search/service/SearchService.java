package com.taotao.search.service;

import com.taotao.dto.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by chenlunwei on 2017/7/9.
 */
public interface SearchService {

	SearchResult search(String q, Integer page, Integer rows) throws IOException, SolrServerException;
}