/*
 Copyright (C) 2011-2015 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Zhao Fangfang
 * @version $Id: News.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 2014-5-27
 */
public class News extends BaseSend {
	private static final long serialVersionUID = -1792220094708675075L;

	private List<String[]> articles = new LinkedList<String[]>();

	public News() {
		super(null, null);
		map.remove("touser");
		map.remove("msgtype");
	}

	/**
	 * 添加图文，严格按照添加顺序排序
	 * 
	 * @param thumbMediaId
	 * @param author
	 * @param title
	 * @param sourceUrl
	 * @param content
	 * @param desc
	 * @param showPic
	 * @return
	 */
	public News addArticle(String thumbMediaId, String author, String title,
			String sourceUrl, String content, String desc, boolean showPic) {
		articles.add(new String[] { 
				thumbMediaId, author, title, sourceUrl, content, desc, showPic ? "1" : "0" });
		return this;
	}

	@Override
	protected JSONObject buildJSON() {
		JSONArray articles = new JSONArray();
		map.put("articles", articles);
		for (String[] a : this.articles) {
			JSONObject item = new JSONObject();
			item.put("thumb_media_id", a[0]);
			if (a[1] != null) {
				item.put("author", a[1]);
			}
			item.put("title", a[2]);
			if (a[3] != null) {
				item.put("content_source_url", a[3]);
			}
			item.put("content", a[4]);
			if (a[5] != null) {
				item.put("digest", a[5]);
			}
			item.put("show_cover_pic", a[6]);
			articles.add(item);
		}
		
		return super.buildJSON();
	}
}
