package cn.symdata.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @Copyright:Copyright (c) 2012-2015
 * @Company:zplay.cn
 * @Title:多线程安全且带连接池的Apache HttpClient
 * @Description:
 * @Author:wangdezhen#zplay.cn
 * @Since:2015年2月11日 下午5:56:21
 * @Version:1.0
 */
public class RemoteContent {
	private static final int TIMEOUT_SECONDS = 5;
	private static final int POOL_SIZE = 20;

	public static String sendGet(String contentUrl) {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000).setConnectTimeout(TIMEOUT_SECONDS * 1000).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(POOL_SIZE).setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(requestConfig).build();
		// 创建httpget.
		HttpGet httpget = new HttpGet(contentUrl);
		// 执行get请求.

		String result = StringUtils.EMPTY;
		try {
			CloseableHttpResponse response = httpClient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				/*
				 * logger.info("--------------------------------------"); //
				 * 打印响应状态 logger.info(response.getStatusLine().toString()); if
				 * (entity != null) { // 打印响应内容长度
				 * System.out.println("Response content length: " +
				 * entity.getContentLength()); // 打印响应内容
				 * System.out.println("Response content: " + result); }
				 * System.out.println("------------------------------------");
				 */
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String sendPost(String contentUrl) {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000).setConnectTimeout(TIMEOUT_SECONDS * 1000).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(POOL_SIZE).setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(requestConfig).build();
		// 创建httpget.
		HttpPost httppost = new HttpPost(contentUrl);
		// 执行get请求.

		String result = StringUtils.EMPTY;
		try {
			CloseableHttpResponse response = httpClient.execute(httppost);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				/*
				 * logger.info("--------------------------------------"); //
				 * 打印响应状态 logger.info(response.getStatusLine().toString()); if
				 * (entity != null) { // 打印响应内容长度
				 * System.out.println("Response content length: " +
				 * entity.getContentLength()); // 打印响应内容
				 * System.out.println("Response content: " + result); }
				 * System.out.println("------------------------------------");
				 */
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * @param json
	 * @param contentUrl
	 * @Description:
	 * @Author:wangdezhen#zplay.cn
	 * @Since:2015年2月11日 下午12:31:08
	 * @Version:1.0
	 */
	public static String sendPost(String json, String contentUrl) {
		// 创建默认的httpClient实例.
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000).setConnectTimeout(TIMEOUT_SECONDS * 1000).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(POOL_SIZE).setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpPost httppost = new HttpPost(contentUrl);
		String result = StringUtils.EMPTY;
		try {
			// uefEntity = new UrlEncodedFormEntity(json, "UTF-8");
			StringEntity stringEntity = new StringEntity(json);
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httppost.setEntity(stringEntity);
			CloseableHttpResponse response = httpClient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				/*
				 * if (entity != null) {
				 * System.out.println("--------------------------------------");
				 * System.out.println("Response content: " + result);
				 * System.out.println("--------------------------------------");
				 * }
				 */
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @param paramsMap
	 * @param contentUrl
	 * @return
	 * @Description:
	 * @Author:wangdezhen#zplay.cn
	 * @Since:2015年3月25日 上午10:53:19
	 * @Version:1.0
	 */
	@SuppressWarnings("deprecation")
	public static String sendPostByParams(Map<String, String> paramsMap, String contentUrl) {
		// 创建默认的httpClient实例.
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000).setConnectTimeout(TIMEOUT_SECONDS * 1000).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(POOL_SIZE).setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpPost httppost = new HttpPost(contentUrl);
		String result = StringUtils.EMPTY;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (String key : paramsMap.keySet()) {
				params.add(new BasicNameValuePair(key, paramsMap.get(key)));
			}
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			CloseableHttpResponse response = httpClient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
