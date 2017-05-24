package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 获取公众号 access_token
 * 
 * @author Zhao Fangfang
 * @version $Id: Token.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public class Token extends BaseApi {

	public Token(ApiInvoker apiInvoker) {
		super("https://api.weixin.qq.com/cgi-bin/token", apiInvoker);
		this.grant_type("client_credential");
	}

	public Token grant_type(String value) {
		return (Token) addParam("grant_type", value);
	}

	public Token appid(String value) {
		return (Token) addParam("appid", value);
	}

	public Token secret(String value) {
		return (Token) addParam("secret", value);
	}
}
