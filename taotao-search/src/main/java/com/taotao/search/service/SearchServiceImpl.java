package com.taotao.search.service;

import com.taotao.dto.SearchResult;
import com.taotao.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by chenlunwei on 2017/7/9.
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String q, Integer page, Integer rows) throws IOException, SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(q);
		
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		solrQuery.set("df","key_words");
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red;\">");
		solrQuery.setHighlightSimplePost("</em>");

		SearchResult result = searchDao.search(solrQuery);
		long recordCount = result.getRecordCount();
		long pageCount = recordCount/rows;
		if(recordCount%rows>0){
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurrentPage(page);
		return result;
	}
}
