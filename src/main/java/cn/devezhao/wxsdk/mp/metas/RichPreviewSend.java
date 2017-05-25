package cn.devezhao.wxsdk.mp.metas;

import com.alibaba.fastjson.JSONObject;

/**
 * 图文消息预览
 * 
 * @author Chen Qun
 * @since 2015-9-14
 * @version $Id$
 */
public class RichPreviewSend extends TextPreviewSend {

	private static final long serialVersionUID = 6861009222215646422L;

	public RichPreviewSend() {
		super();
		map.put("msgtype", "mpnews");
	}

	@Override
	public TextSend setContent(String content) {
		throw new UnsupportedOperationException();
	}

	public RichPreviewSend media_id(String media_id) {
		JSONObject mpnews = new JSONObject();
		mpnews.put("media_id", media_id);
		map.put("mpnews", mpnews);
		return this;
	}
}
