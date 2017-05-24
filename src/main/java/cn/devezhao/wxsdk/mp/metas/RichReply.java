/*
 Copyright (C) 2011-2014 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Element;

/**
 * 
 * @author Zhao Fangfang
 * @version $Id: RichReply.java 181 2016-05-20 11:22:01Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class RichReply extends BaseReply implements Rich {
	private static final long serialVersionUID = 1727342165315697943L;

	private List<String[]> articles = new LinkedList<String[]>();

	public RichReply(String toUserName, String fromUserName) {
		super(toUserName, fromUserName, "news");
	}

	@Override
	public Rich addArticle(String title, String description, String picUrl, String url) {
		articles.add(new String[] { title, description, picUrl, url });
		return this;
	}

	@Override
	public String toXML() {
		map.put("ArticleCount", articles.size());
		Element root = buildXML();
		
		Element Articles = root.addElement("Articles");
		for (String[] a : articles) {
			Element item = Articles.addElement("item");
			item.addElement("Title").setText(a[0]);
			if (a[1] != null) {
				item.addElement("Description").setText(a[1]);
			}
			item.addElement("PicUrl").setText(a[2]);
			
			// 不自动带openid
			String url = a[3];
			if (!url.contains("unopenid")) {
				url += "#openid=" + map.get("ToUserName");
			}
			item.addElement("Url").setText(url);
		}
		return root.asXML();
	}
}
