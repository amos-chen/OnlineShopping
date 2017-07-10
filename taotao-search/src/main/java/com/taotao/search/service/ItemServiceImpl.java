package com.taotao.search.service;

import com.taotao.dto.Item;
import com.taotao.search.mapper.ItemDaoMapper;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenlunwei on 2017/7/9.
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDaoMapper itemDaoMapper;

	@Autowired
	private HttpSolrClient solrClient;

	@Override
	public Integer addDocument() {
		List<Item> itemList = itemDaoMapper.queryItem();
		try {
			for (Item item : itemList) {
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSellPoint());
				document.setField("item_price", item.getPrice());
				document.setField("item_catgory_name", item.getCategoryName());
				document.setField("item_description", item.getDescription());
				document.setField("item_image", item.getImage());
				solrClient.add(document);
			}
			UpdateResponse response = solrClient.commit();
			return response.getQTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
