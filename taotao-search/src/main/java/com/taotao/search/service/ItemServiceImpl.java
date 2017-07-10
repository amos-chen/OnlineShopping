package com.taotao.search.service;

import com.taotao.dto.Item;
import com.taotao.search.mapper.ItemDaoMapper;
import com.taotao.utils.JsonUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Long addDocument() {
        List<Item> itemList = itemDaoMapper.queryItemList();
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
            //getQTime，获取在solr内查询的时间；
            //getElapsedTime，除了包含查询时间，还包含传输、序列化或反序列化等时间。
            UpdateResponse response = solrClient.commit();
            return response.getElapsedTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long addAndUpdateDocument(String itemId) {
        Item item = itemDaoMapper.queryItem(Long.parseLong(itemId));
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", item.getId());
            document.setField("item_title", item.getTitle());
            document.setField("item_sell_point", item.getSellPoint());
            document.setField("item_price", item.getPrice());
            document.setField("item_catgory_name", item.getCategoryName());
            document.setField("item_description", item.getDescription());
            document.setField("item_image", item.getImage());
            solrClient.add(document);
            UpdateResponse response = solrClient.commit();
            return response.getElapsedTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long deleteDocument(String itemIdList) {
        try {
            String[] strings = JsonUtils.jsonToPojo(itemIdList,String[].class);
            for (String str : strings) {
                solrClient.deleteById(str);
            }
            UpdateResponse response = solrClient.commit();
            return response.getElapsedTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
