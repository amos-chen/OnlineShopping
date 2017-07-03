package com.taotao.portal.service;

import com.taotao.pojo.TbContent;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlunwei on 2017/7/2.
 */
@Service
public class PortalContentServiceImpl implements PortalContentService {

	@Value("${BASIC_URL}")
	private String BASIC_URL;
	@Value("${BIG_AD_URL}")
	private String BIG_AD_URL;


	@Override
	public String getContentList() {
		//通过httpclientutil从rest服务中获取contentList，获取的内容为json字符串
		String result = HttpClientUtil.doGet(BASIC_URL + BIG_AD_URL);
		//把转换后的list转换成前台需要的json格式
		try {
			List<TbContent> contentList = JsonUtils.jsonToList(result, TbContent.class);
			List<Map> resultList = new ArrayList<>();
			for (TbContent tbContent : contentList) {
				Map resultMap = new HashMap<>();
				resultMap.put("src", tbContent.getPic());
				resultMap.put("srcB", tbContent.getPic2());
				resultMap.put("hight", 240);
				resultMap.put("width", 670);
				resultMap.put("widthB", 550);
				resultMap.put("hightB", 240);
				resultMap.put("alt", tbContent.getTitleDesc());
				resultList.add(resultMap);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
