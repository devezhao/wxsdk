/*
 Copyright (C) 2011-2014 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

/**
 * 图文消息
 * 
 * @author Zhao Fangfang
 * @version $Id: Rich.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-19
 */
public interface Rich {

	/**
	 * 添加图文，严格按照添加顺序排序
	 * 
	 * @param title
	 * @param description
	 * @param picUrl
	 * @param url
	 * @return
	 */
	Rich addArticle(String title, String description, String picUrl, String url);
}
