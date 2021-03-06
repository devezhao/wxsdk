package cn.devezhao.wxsdk.mp.metas;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import cn.devezhao.wxsdk.utils.ApiUtils;

/**
 * @author Chen Qun
 * @since 2015-11-27
 * @version $Id$
 */
public class ImageReply extends BaseReply{
	
	private static final long serialVersionUID = 5234213874436798721L;

	public ImageReply(String toUserName, String fromUserName) {
		super(toUserName, fromUserName, "image");
	}

	public ImageReply setMediaId(String mediaId ) {
		map.put("MediaId", mediaId);
		return this;
	}
	
	@Override
	public String toXML() {
		Document xml = ApiUtils.parseDocument("<xml/>");
		Element root = xml.getRootElement();
		for (Map.Entry<String, Object> e : map.entrySet()) {
			Object v = e.getValue();
			if (v instanceof Map) {
			} else {
				if(StringUtils.equalsIgnoreCase(e.getKey(), "MediaId")){
					root.addElement("Image").addElement(e.getKey()).setText(v.toString());
				}else{
					root.addElement(e.getKey()).setText(v.toString());
				}
			}
		}
		return root.asXML();
	}
}