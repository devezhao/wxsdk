package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 获取粉丝列表（仅包含 openid）
 * 
 * @author Zhao Fangfang
 * @version $Id: UserGet.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public class UserGet extends BaseApi implements AuthzApi<UserGet> {

	public UserGet(ApiInvoker apiInvoker) {
		super("https://api.weixin.qq.com/cgi-bin/user/get", apiInvoker);
	}
	
	@Override
	public UserGet access_token(String value) {
		return (UserGet) addParam("access_token", value);
	}

	public UserGet next_openid(String value) {
		return (UserGet) addParam("next_openid", value);
	}
}
