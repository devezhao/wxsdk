package cn.devezhao.wxsdk.mp.metas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.devezhao.wxsdk.utils.ApiUtils;

/**
 * XML格式的主动发送消息
 * 
 * @author Zhao Fangfang
 * @version $Id: BaseReply.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class BaseReply implements Serializable {
	private static final long serialVersionUID = 1547394269515042977L;
	
	protected Map<String, Object> map = new LinkedHashMap<String, Object>();

	public BaseReply(String toUserName, String fromUserName, String msgType) {
		map.put("ToUserName", toUserName);
		map.put("FromUserName", fromUserName);
		map.put("MsgType", msgType);
		map.put("CreateTime", System.currentTimeMillis() / 1000);
	}

	protected Element buildXML() {
		Document xml = ApiUtils.parseDocument("<xml/>");
		Element root = xml.getRootElement();
		for (Map.Entry<String, Object> e : map.entrySet()) {
			Object v = e.getValue();
			if (v instanceof Map) {
			} else {
				root.addElement(e.getKey()).setText(v.toString());
			}
		}
		return root;
	}
	
	public String toXML() {
		return buildXML().asXML();
	}
}
