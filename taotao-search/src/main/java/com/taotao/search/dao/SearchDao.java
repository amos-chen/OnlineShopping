package com.taotao.search.dao;

import com.taotao.dto.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by chenlunwei on 2017/7/9.
 */
public interface SearchDao {

	SearchResult search(SolrQuery query) throws IOException, SolrServerException;
}
