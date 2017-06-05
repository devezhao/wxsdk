package cn.devezhao.wxsdk.wxx;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.Result;
import cn.devezhao.wxsdk.mp.AuthzApi;
import cn.devezhao.wxsdk.mp.BaseApi;

/**
 * 小程序模板消息发送
 * 
 * @author zhaofang123@gmail.com
 * @since 06/06/2017
 */
public class TemplateSend extends BaseApi implements AuthzApi<TemplateSend> {

	public TemplateSend(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send", invoker);
	}

	@Override
	public TemplateSend access_token(String value) {
		return (TemplateSend) addParam("access_token", value);
	}
	
	public Result send(String postJson) {
		setPostJson(postJson);
		return execJson();
	}
	
	public Result send(TemplateMsg msg) {
		return send(msg.toJson());
	}
}
