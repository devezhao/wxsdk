package cn.devezhao.wxsdk.mp;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.Result;

/**
 * 客服消息
 * 
 * @author zhaofang123@gmail.com
 * @since 06/06/2017
 */
public class CustomSend extends BaseApi implements AuthzApi<CustomSend> {

	public CustomSend(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/message/custom/send", invoker);
	}

	@Override
	public CustomSend access_token(String value) {
		return (CustomSend) addParam("access_token", value);
	}

	/**
	 * @param touser
	 * @param content
	 * @return
	 */
	public Result sendText(String touser, String content) {
		Map<String, Object> map = new HashMap<>();
		map.put("touser", touser);
		map.put("msgtype", "text");
		Map<String, String> text = new HashMap<>();
		text.put("content", content);
		map.put("text", text);
		
		String json = JSON.toJSONString(map);
		setPostJson(json);
		return execJson();
	}
	
	/**
	 * @param touser
	 * @param mediaId
	 * @return
	 */
	public Result sendImage(String touser, String mediaId) {
		return null;
	}
}
