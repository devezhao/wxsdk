package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 上传图文消息
 * 
 * @author Zhao Fangfang
 * @version $Id: MediaUploadnews.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 2014-5-27
 */
public class MediaUploadnews extends BaseApi implements AuthzApi<MediaUploadnews> {

	public MediaUploadnews(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/media/uploadnews", invoker);
	}
	
	@Override
	public MediaUploadnews access_token(String value) {
		return (MediaUploadnews) addParam("access_token", value);
	}
}
