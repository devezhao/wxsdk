/*
 Copyright (C) 2011-2015 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Zhao Fangfang
 * @version $Id: TextMasssend.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 2014-5-27
 */
public class TextMasssend extends TextSend {
	private static final long serialVersionUID = 8421748150858771401L;

	public static final int MAX_SEND_USER = 10000;
	
	private Set<String> toUsers = new HashSet<String>();
	
	public TextMasssend() {
		super(null);
		map.remove("touser");
	}
	
	/**
	 * 限定发送用户组（setGroupId、addToUser/s只可使用一个，同时设置，优先setGroupId）
	 * 
	 * @param groupId
	 * @return
	 * @see #addToUser(String)
	 */
	public TextMasssend setGroupId(long groupId) {
		JSONObject filter = new JSONObject();
		filter.put("group_id", groupId + "");
		map.put("filter", filter);
		return this;
	}
	
	/**
	 * 添加发送用户（setGroupId、addToUser/s只可使用一个，同时设置，优先setGroupId）
	 * 
	 * @param openid
	 * @return 返回当前的发送用户数量
	 * @see #setGroupId(long)
	 */
	public int addToUser(String openid) {
		toUsers.add(openid);
		int size = toUsers.size();
		if (size > 10000) {
			throw new IllegalArgumentException("MAX_SEND_USER " + MAX_SEND_USER);
		}
		return size;
	}
	
	/**
	 * 添加发送用户（setGroupId、addToUser/s只可使用一个，同时设置，优先setGroupId）
	 * 
	 * @param openids
	 * @return 返回当前的发送用户数量
	 * @see #setGroupId(long)
	 * @see #addToUser(String)
	 */
	public int addToUsers(String openids[]) {
		int size = toUsers.size();
		for (String o : openids) {
			size = addToUser(o);
		}
		return size;
	}
	
	@Override
	protected JSONObject buildJSON() {
		if (map.containsKey("filter")) {
		} else if (!toUsers.isEmpty()) {
			map.put("touser", toUsers);
		}
		return super.buildJSON();
	}
}
