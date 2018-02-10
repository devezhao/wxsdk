package cn.devezhao.wxsdk.mp;

import com.alibaba.fastjson.JSON;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 02/10/2018
 */
public class Shorturl extends BaseApi implements AuthzApi<Shorturl> {

	public Shorturl(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/shorturl", invoker);
	}

	@Override
	public Shorturl access_token(String value) {
		return (Shorturl) addParam("access_token", value);
	}
	
	public Shorturl long_url(String value) {
		return (Shorturl) addParam("long_url", value);
	}
	
	@Override
	protected String getPostJson() {
		addParam("action", "long2short");
		return JSON.toJSONString(getParams());
	}
}
