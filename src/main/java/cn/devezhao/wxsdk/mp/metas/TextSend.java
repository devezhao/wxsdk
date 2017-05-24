/*
 Copyright (C) 2011-2014 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Zhao Fangfang
 * @version $Id: TextSend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class TextSend extends BaseSend {
	private static final long serialVersionUID = 5844969640338096642L;

	public TextSend(String touser) {
		super(touser, "text");
	}

	public TextSend setContent(String content) {
		JSONObject text = new JSONObject();
		text.put("content", content);
		map.put("text", text);
		return this;
	}
}
