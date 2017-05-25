package cn.devezhao.wxsdk.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import cn.devezhao.wxsdk.ApiException;
import cn.devezhao.wxsdk.JsonResult;
import cn.devezhao.wxsdk.Result;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 05/24/2017
 */
public class HttpClientEx {
	
	private HttpClient httpClient;
	private String charset;

	/**
	 * @param timeout
	 * @param charset
	 */
	public HttpClientEx(int timeout, String charset) {
		this.charset = charset == null ? "utf-8" : charset;
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		RequestConfig rc = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
		this.httpClient = HttpClients.custom()
				.setConnectionManager(cm)
				.setDefaultRequestConfig(rc)
				.build();
	}
	
	/**
	 * @return
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}
	
	/**
	 * @param url
	 * @return
	 */
	public Result get(String url) {
		HttpGet httpGet = new HttpGet(url);
		return execMethod(httpGet);
	}
	
	/**
	 * @param url
	 * @param data
	 * @return
	 */
	public Result post(String url, Object data) {
		HttpPost httpPost = new HttpPost();
		if (data != null) {
			httpPost.setEntity(new StringEntity(data.toString(), charset));
		}
		return execMethod(httpPost);
	}
	
	/**
	 * @param request
	 * @return
	 */
	public Result execMethod(HttpUriRequest request) {
		try {
			HttpResponse resp = httpClient.execute(request);
			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new ApiException("无效 HTTP 状态: " + resp.getStatusLine());
			}
			
			String r = EntityUtils.toString(resp.getEntity(), charset);
			return new JsonResult(r, resp.getAllHeaders());
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}
	
	/**
	 * @param uri
	 * @param timeout
	 * @return
	 */
	public byte[] readBinary(String uri, int timeout) {
		HttpGet get = new HttpGet(uri);
		get.setConfig(RequestConfig.custom().setSocketTimeout(timeout).build());
		
		try {
			HttpResponse resp = httpClient.execute(get);
			if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new ApiException("无效 HTTP 状态: " + resp.getStatusLine());
			}
			
			return EntityUtils.toByteArray(resp.getEntity());
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}
}
