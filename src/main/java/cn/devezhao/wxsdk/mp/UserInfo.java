package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 获取用户基本信息
 * 
 * @author Zhao Fangfang
 * @version $Id: UserInfo.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public class UserInfo extends BaseApi implements AuthzApi<UserInfo> {

	public UserInfo(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/user/info", invoker);
		this.lang("zh_CN");
	}

	@Override
	public UserInfo access_token(String value) {
		return (UserInfo) addParam("access_token", value);
	}

	public UserInfo openid(String value) {
		return (UserInfo) addParam("openid", value);
	}

	public UserInfo lang(String value) {
		return (UserInfo) addParam("lang", value);
	}
}
