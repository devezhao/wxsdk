package cn.devezhao.wxsdk;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * API调用者
 * 
 * @author Zhao Fangfang
 * @version $Id: ApiInvoker.java 60 2015-08-26 09:21:51Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public class ApiInvoker {
	
	final private HttpClientFetcher httpClientFetcher;
	private boolean throwError;
	private String apiUrlBase;
	
	/**
	 */
	public ApiInvoker() {
		this(false, null);
	}
	
	/**
	 * @param throwError
	 */
	public ApiInvoker(boolean throwError) {
		this(throwError, null);
	}
	
	/**
	 * @param throwError
	 * @param apiUrlBase
	 */
	public ApiInvoker(boolean throwError, String apiUrlBase) {
		this.httpClientFetcher = new HttpClientFetcher();
		this.throwError = throwError;
		this.apiUrlBase = apiUrlBase;
	}
	
	/**
	 * 获取HttpClientFetcher
	 * 
	 * @return
	 */
	public HttpClientFetcher getHttpClientFetcher() {
		return httpClientFetcher;
	}
	
	/**
	 * 调用接口错误或返回错误时，是否自动抛出异常
	 * 
	 * @return
	 */
	public boolean isThrowError() {
		return throwError;
	}
	
	/**
	 * 调用<tt>GET</tt>方法
	 * 
	 * @param apiUrl
	 * @param params
	 * @return
	 */
	public Result invokeGet(String apiUrl, Map<String, String> params) {
		if (apiUrlBase != null) {
			apiUrl = apiUrl.replaceFirst("https://api.weixin.qq.com/", apiUrlBase);
		}
		final String uri = ApiUtils.toApiInvokeURI(apiUrl, params, null);
		HttpMethod method = new GetMethod(uri);
		
		Result result = getHttpClientFetcher().executeMethod(method);
		if (isThrowError()) {
			result.throwIfError();
		}
		return result;
	}
	
	/**
	 * 调用<tt>POST</tt>方法
	 * 
	 * @param apiUrl
	 * @param params
	 * @return
	 */
	public Result invokePost(String apiUrl, Map<String, String> params) {
		return invokePost(apiUrl, params, null);
	}
	
	/**
	 * 调用<tt>POST</tt>方法, 可选择指定大文本参数名称
	 * 
	 * @param apiUrl
	 * @param params
	 * @param longtextParamName
	 * @return
	 */
	public Result invokePost(String apiUrl, Map<String, String> params, String longtextParamName) {
		if (apiUrlBase != null) {
			apiUrl = apiUrl.replaceFirst("https://api.weixin.qq.com/", apiUrlBase);
		}
		final String uri = ApiUtils.toApiInvokeURI(apiUrl, params, longtextParamName);
		PostMethod method = new PostMethod(uri);
		if (longtextParamName != null) {
			String v = params.get(longtextParamName);
			if (v != null) {
				method.setParameter(longtextParamName, v);
			}
		}
		
		Result result = getHttpClientFetcher().executeMethod(method);
		if (isThrowError()) {
			result.throwIfError();
		}
		return result;
	}
	
	/**
	 * 调用<tt>POST</tt>方法, 可以提交JSON格式的数据
	 * 
	 * @param apiUrl
	 * @param params
	 * @param postJson
	 * @return
	 */
	public Result invokePostJson(String apiUrl, Map<String, String> params, String postJson) {
		if (apiUrlBase != null) {
			apiUrl = apiUrl.replaceFirst("https://api.weixin.qq.com/", apiUrlBase);
		}
		final String uri = ApiUtils.toApiInvokeURI(apiUrl, params, null);
		PostMethod method = new PostMethod(uri);
		StringRequestEntity entity = null;
		try {
			entity = new StringRequestEntity(postJson, "application/json", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// IGNORE...
		}
		method.setRequestEntity(entity);
		
		Result result = getHttpClientFetcher().executeMethod(method);
		if (isThrowError()) {
			result.throwIfError();
		}
		return result;
	}
}
