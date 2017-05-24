package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 使用粉丝的授权码获取粉丝信息
 * 
 * @author Pengrl
 * @version $Id: OauthAccessToken.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-5-13
 */
public class OauthAccessToken extends BaseApi {

	public OauthAccessToken(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/sns/oauth2/access_token", invoker);
		this.grant_type("authorization_code");
	}

	public OauthAccessToken grant_type(String value) {
		return (OauthAccessToken) addParam("grant_type", value);
	}

	public OauthAccessToken appid(String value) {
		return (OauthAccessToken) addParam("appid", value);
	}

	public OauthAccessToken secret(String value) {
		return (OauthAccessToken) addParam("secret", value);
	}
	
	public OauthAccessToken code(String value) {
		return (OauthAccessToken) addParam("code", value);
	}
}
