package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.Result;

/**
 * @author Pengrl
 * @version $Id: TemplateSend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-12-20
 */
public class TemplateSend extends BaseApi implements AuthzApi<TemplateSend> {

	public TemplateSend(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/message/template/send", invoker);
	}

	@Override
	public TemplateSend access_token(String value) {
		return (TemplateSend) addParam("access_token", value);
	}

	public Result send(String postJson) {
		setPostJson(postJson);
		return execJson();
	}
}
