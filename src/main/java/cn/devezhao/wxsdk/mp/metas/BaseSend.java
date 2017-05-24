/*
 Copyright (C) 2011-2014 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON格式的被动回复消息
 * 
 * @author Zhao Fangfang
 * @version $Id: BaseSend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class BaseSend implements Serializable {

	private static final long serialVersionUID = 1547394269515042977L;

	protected Map<String, Object> map = new LinkedHashMap<String, Object>();

	public BaseSend(String touser, String msgtype) {
		map.put("touser", touser);
		map.put("msgtype", msgtype);
	}
	
	protected JSONObject buildJSON() {
		JSONObject json = new JSONObject(false);
		json.putAll(map);
		return json;
	}
	
	public String toJSON() {
		return buildJSON().toJSONString();
	}
}
