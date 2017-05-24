package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 发送客服消息
 * 
 * @author Zhao Fangfang
 * @version $Id: MessageCustomSend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class MessageCustomSend extends BaseApi implements AuthzApi<MessageCustomSend> {

	public MessageCustomSend(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/message/custom/send", invoker);
	}
	
	@Override
	public MessageCustomSend access_token(String value) {
		return (MessageCustomSend) addParam("access_token", value);
	}
}
