package com.taotao.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlunwei on 2017/7/2.
 */
public class HttpClientUtil {

	//带参数的get方法
	public static String doGet(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (params != null) {
				for (String key : params.keySet()) {
					uriBuilder.setParameter(key, params.get(key));
				}
			}
			URI uri = uriBuilder.build();
			HttpGet httpGet = new HttpGet(uri);
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//不带参数的get方法
	public static String doGet(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}



	//带参数的post方法
	public static String doPost(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			if (params != null) {
				List<NameValuePair> nameValuePairList = new ArrayList<>();
				for (String key : params.keySet()) {
					nameValuePairList.add(new BasicNameValuePair(key, params.get(key)));
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList);
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//不带参数的post方法
	public static String doPost(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String doPostJson(String url, String json) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			if (json != null) {
				StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
