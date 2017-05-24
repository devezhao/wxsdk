/*
 Copyright (c) 2015 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import com.alibaba.fastjson.JSONObject;
import com.qidapp.wxsdk.api.meta.TextSend;

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
