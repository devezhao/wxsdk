package cn.devezhao.wxsdk.wxx;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 06/06/2017
 */
public class TemplateMsg {
	
	private Map<String, Object> data = new HashMap<>();

	/**
	 * @param touser
	 * @param templateId
	 */
	public TemplateMsg(String touser, String templateId) {
		this(touser, templateId, null, null);
	}

	/**
	 * @param touser
	 * @param templateId
	 * @param formId
	 * @param page
	 */
	public TemplateMsg(String touser, String templateId, String formId, String page) {
		data.put("touser", touser);
		data.put("template_id", templateId);
		if (formId != null) {
			data.put("form_id", formId);
		}
		if (page != null) {
			data.put("page", page);
		}
		
		data.put("data", new HashMap<String, Map<String, String>>());
	}
	
	public TemplateMsg setFormId(String formId) {
		data.put("form_id", formId);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public TemplateMsg addKeyword(String key, String value, String color) {
		Map<String, Map<String, String>> data1 = ((Map<String, Map<String, String>>) data.get("data"));
		Map<String, String> m = new HashMap<>();
		m.put("value", value);
		m.put("color", color == null ? "#000000" : color);
		data1.put(key, m);
		return this;
	}
	
	public TemplateMsg addKeyword(String key, String value) {
		return addKeyword(key, value, "#000000");
	}
	
	@Deprecated
	public TemplateMsg addKeyword(int keyIndex, String value) {
		return addKeyword("keyword" + keyIndex, value);
	}
	
	public TemplateMsg setEmphasisKeyword(String key) {
		if (!key.endsWith(".DATA")) {
			key += ".DATA";
		}
		data.put("emphasis_keyword", key);
		return this;
	}
	
	public String toJson() {
		return JSON.toJSONString(data);
	}
}
