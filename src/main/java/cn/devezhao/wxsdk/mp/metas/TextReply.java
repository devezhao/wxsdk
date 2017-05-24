/*
 Copyright (C) 2011-2014 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

/**
 * @author Zhao Fangfang
 * @version $Id: TextReply.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class TextReply extends BaseReply {
	private static final long serialVersionUID = -2018906425158498177L;

	public TextReply(String toUserName, String fromUserName) {
		super(toUserName, fromUserName, "text");
	}

	public TextReply setContent(String content) {
		map.put("Content", content);
		return this;
	}
}
