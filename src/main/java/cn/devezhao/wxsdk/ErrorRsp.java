package cn.devezhao.wxsdk;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用API错误
 * 
 * @author Zhao Fangfang
 * @version $Id: ErrorRsp.java 89 2015-10-14 03:21:01Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-17
 */
public class ErrorRsp {
	
	static final Map<Integer, String> ERRORs = new HashMap<Integer, String>();
	static {
		ERRORs.put(40001, "验证失败，可能是 TOKEN 过期，请尝试重新配置公众号");
		ERRORs.put(48001, "您的公众号（类型）不能使用该功能，因为没有此功能的开发者权限");
		ERRORs.put(40125, "APPSECRET 由于长期不使用已过期（t.cn/RAEkdVq），请至公众号后台重置 APPSECRET");
	}

	public final Integer ErrorCode;
	public final String ErrorMsg;

	public ErrorRsp(int errcode, String errmsg) {
		this.ErrorCode = errcode;
		this.ErrorMsg = errmsg;
	}
	
	@Override
	public String toString() {
		return ErrorCode + "#" + (ERRORs.containsKey(ErrorCode) ? ERRORs.get(ErrorCode) : ErrorMsg);
	}
}
