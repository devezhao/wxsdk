/*
 Copyright (C) 2011-2014 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Zhao Fangfang
 * @version $Id: ImageSend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class ImageSend extends BaseSend {
	private static final long serialVersionUID = 5844969640338096642L;

	public ImageSend(String touser) {
		super(touser, "image");
	}

	public ImageSend setMedia(String media) {
		JSONObject image = new JSONObject();
		image.put("media_id", media);
		map.put("image", image);
		return this;
	}
}
