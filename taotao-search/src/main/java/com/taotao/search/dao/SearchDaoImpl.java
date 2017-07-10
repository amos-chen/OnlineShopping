package com.taotao.search.dao;

import com.taotao.dto.Item;
import com.taotao.dto.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlunwei on 2017/7/9.
 */
@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private HttpSolrClient solrClient;

	@Override
	public SearchResult search(SolrQuery query) throws IOException, SolrServerException {
		//创建SearchResult对象，最为返回值
		SearchResult result = new SearchResult();
		//solr通过条件进行查询，获取QueryResponse对象
		QueryResponse queryResponse = solrClient.query(query);
		//通过QueryResult获取高亮对象信息
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

		SolrDocumentList documents = queryResponse.getResults();
		result.setRecordCount(documents.getNumFound());
		List<Item> itemList = new ArrayList<>();
		for (SolrDocument document : documents) {
			Item item = new Item();
			item.setId((String) document.get("id"));
			item.setCategoryName((String) document.get("item_catgory_name"));
			item.setDescription((String) document.get("item_description"));
			item.setImage((String) document.get("item_image"));
			item.setPrice((long) document.get("item_price"));
			item.setSellPoint((String) document.get("item_sell_point"));
			List<String> highlightlist = highlighting.get(document.get("id")).get("item_title");

			if (highlightlist != null && highlightlist.size() > 0) {
				item.setTitle(highlightlist.get(0));
			} else {
				item.setTitle((String) document.get("item_title"));
			}

			itemList.add(item);
		}
		result.setItems(itemList);
		return result;
	}
}
