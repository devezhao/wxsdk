package cn.devezhao.wxsdk.mp;

import java.util.HashMap;
import java.util.Map;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.Result;

/**
 * API 调用基础类
 * 
 * @author Zhao Fangfang
 * @version $Id: BaseApi.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public abstract class BaseApi {

	private final String apiUrl;
	private final ApiInvoker apiInvoker;
	
	private final Map<String, String> params = new HashMap<String, String>();
	private String postJson;
	
	/**
	 * @param apiUrl
	 * @param invoker
	 */
	public BaseApi(String apiUrl, ApiInvoker invoker) {
		this.apiUrl = apiUrl;
		this.apiInvoker = invoker;
	}
	
	public String getApiUrl() {
		return apiUrl;
	}
	
	public ApiInvoker getApiInvoker() {
		return apiInvoker;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public BaseApi addParam(String key, String value) {
		this.params.put(key, value);
		return this;
	}
	
	public BaseApi setPostJson(String json) {
		this.postJson = json; 
		return this;
	}
	
	protected String getPostJson() {
		return postJson;
	}
	
	public Result exec() {
		return getApiInvoker().invokeGet(getApiUrl(), getParams());
	}
	
	public Result execPost() {
		return getApiInvoker().invokePost(getApiUrl(), getParams(), getPostJson());
	}
	
	public Result execJson() {
		return execPost();
	}
}
