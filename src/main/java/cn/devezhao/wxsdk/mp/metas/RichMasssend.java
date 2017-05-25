package cn.devezhao.wxsdk.mp.metas;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Zhao Fangfang
 * @version $Id: RichMasssend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 2014-5-27
 */
public class RichMasssend extends TextMasssend {
	private static final long serialVersionUID = 6861009222215646422L;

	public RichMasssend() {
		super();
		map.put("msgtype", "mpnews");
	}
	
	@Override
	public TextSend setContent(String content) {
		throw new UnsupportedOperationException();
	}
	
	public RichMasssend media_id(String media_id) {
		JSONObject mpnews = new JSONObject();
		mpnews.put("media_id", media_id);
		map.put("mpnews", mpnews);
		return this;
	}
}