package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 群发消息
 * 
 * @author Zhao Fangfang
 * @version $Id: MessageMassSend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 2014-5-27
 */
public class MessageMassSend extends BaseApi implements AuthzApi<MessageMassSend> {

	public MessageMassSend(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/message/mass/send", invoker);
	}

	@Override
	public MessageMassSend access_token(String value) {
		return (MessageMassSend) addParam("access_token", value);
	}
}
