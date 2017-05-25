package cn.devezhao.wxsdk.mp.metas;

import com.alibaba.fastjson.JSONObject;

/**
 * 文本消息发送预览
 * 
 * @author Chen Qun
 * @since 2015-9-14
 * @version $Id$
 */
public class TextPreviewSend extends TextSend {

	private static final long serialVersionUID = 3204450622382058175L;

	private String touser = "";

	public TextPreviewSend() {
		super(null);
		map.remove("touser");
	}

	public int addToUser(String openid) {
		this.touser = openid;
		return 1;
	}

	@Override
	protected JSONObject buildJSON() {
		if (!this.touser.isEmpty()) {
			map.put("touser", this.touser);
		}
		return super.buildJSON();
	}
}
