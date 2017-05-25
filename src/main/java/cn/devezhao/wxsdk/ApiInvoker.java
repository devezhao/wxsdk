package cn.devezhao.wxsdk;

import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import cn.devezhao.wxsdk.utils.ApiUtils;
import cn.devezhao.wxsdk.utils.HttpClientEx;

/**
 * API 调用者
 * 
 * @author Zhao Fangfang
 * @version $Id: ApiInvoker.java 60 2015-08-26 09:21:51Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public class ApiInvoker {
	
	private HttpClientEx httpClient;
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
		this.httpClient = new HttpClientEx(10 * 1000, "utf-8");
		this.throwError = throwError;
		this.apiUrlBase = apiUrlBase;
	}
	
	/**
	 * 获取 <tt>HttpClientEx</tt>
	 * 
	 * @return
	 */
	public HttpClientEx getHttpClient() {
		return httpClient;
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
		String uri = ApiUtils.toApiInvokeURI(apiUrl, params, null);
		Result result = httpClient.get(uri);
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
	 * @param longtextParamName
	 * @return
	 */
	public Result invokePost(String apiUrl, Map<String, String> params) {
		if (apiUrlBase != null) {
			apiUrl = apiUrl.replaceFirst("https://api.weixin.qq.com/", apiUrlBase);
		}
		String uri = ApiUtils.toApiInvokeURI(apiUrl, params, null);
		Result result = httpClient.post(uri, null);
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
	public Result invokePost(String apiUrl, Map<String, String> params, String postJson) {
		if (apiUrlBase != null) {
			apiUrl = apiUrl.replaceFirst("https://api.weixin.qq.com/", apiUrlBase);
		}
		String uri = ApiUtils.toApiInvokeURI(apiUrl, params, null);
		
		HttpPost httpPost = new HttpPost(uri);
		if (postJson != null) {
			httpPost.setEntity(new StringEntity(postJson, ContentType.APPLICATION_JSON));
		}
		
		Result result = getHttpClient().execMethod(httpPost);
		if (isThrowError()) {
			result.throwIfError();
		}
		return result;
	}
}
