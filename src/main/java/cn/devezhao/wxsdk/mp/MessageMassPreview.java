package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 群发消息预览
 * 
 * @author Chen Qun
 * @since 2015-9-14
 * @version $Id$
 */
public class MessageMassPreview extends BaseApi implements AuthzApi<MessageMassPreview> {

	public MessageMassPreview(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/message/mass/preview", invoker);
	}

	@Override
	public MessageMassPreview access_token(String value) {
		return (MessageMassPreview) addParam("access_token", value);
	}
}
