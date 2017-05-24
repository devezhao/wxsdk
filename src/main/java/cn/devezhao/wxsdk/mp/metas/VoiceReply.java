/*
 Copyright (c) 2015 QIDAPP.com. All rights reserved.
 QIDAPP.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qidapp.wxsdk.api.meta;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.qdss.commons.util.xml.XmlHelper;

/**
 * @author Chen Qun
 * @since 2015-11-27
 * @version $Id$
 */
public class VoiceReply extends BaseReply{
	
	private static final long serialVersionUID = 8010865891589979310L;

	public VoiceReply(String toUserName, String fromUserName) {
		super(toUserName, fromUserName, "voice");
	}

	public VoiceReply setMediaId(String mediaId ) {
		map.put("MediaId", mediaId);
		return this;
	}
	
	@Override
	public String toXML() {
		Document xml = XmlHelper.parseText("<xml/>");
		Element root = xml.getRootElement();
		for (Map.Entry<String, Object> e : map.entrySet()) {
			Object v = e.getValue();
			if (v instanceof Map) {
			} else {
				if(StringUtils.equalsIgnoreCase(e.getKey(), "MediaId")){
					root.addElement("Voice").addElement(e.getKey()).setText(v.toString());
				}else{
					root.addElement(e.getKey()).setText(v.toString());
				}
			}
		}
		return root.asXML();
	}
}