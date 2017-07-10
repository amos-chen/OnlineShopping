package com.taotao.search.service;

/**
 * Created by chenlunwei on 2017/7/9.
 */
public interface ItemService {

	Long addDocument();

	Long addAndUpdateDocument(String itemId);

	Long deleteDocument(String itemIdList);
}
