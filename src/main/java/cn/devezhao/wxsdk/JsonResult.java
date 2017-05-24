package cn.devezhao.wxsdk;

import org.apache.http.Header;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 接口返回JSON结果
 * 
 * @author Zhao Fangfang
 * @version $Id: JsonResult.java 132 2016-02-04 04:05:18Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public class JsonResult implements Result {

	private final String rawContent;
	private final Header[] headers;
	private JSONObject jsonContent;

	public JsonResult(String content, Header[] headers) {
		this.headers = headers == null ? new Header[0] : headers;
		this.rawContent = content;
	}
	
	public Header[] getHeaders() {
		return headers;
	}

	public String getRawContent() {
		return rawContent;
	}

	@Override
	public String getString(String key) {
		return parseJSON().getString(key);
	}

	@Override
	public Integer getInt(String key) {
		return parseJSON().getInteger(key);
	}

	@Override
	public Long getLong(String key) {
		return parseJSON().getLong(key);
	}

	@Override
	public Double getDouble(String key) {
		return parseJSON().getDouble(key);
	}
	
	public JSONObject getJsonResult() {
		return parseJSON();
	}

	private JSONObject parseJSON() {
		if (jsonContent != null) {
			return jsonContent;
		}
		jsonContent = JSON.parseObject(getRawContent());
		return jsonContent;
	}
	
	@Override
	public ErrorRsp hasError() {
		Long errcode = getLong("errcode");
		if (errcode != null && errcode > 0) {
			return new ErrorRsp(errcode.intValue(), getString("errmsg"));
		}
		return null;
	}
	
	@Override
	public Result throwIfError() {
		ErrorRsp rsp = hasError();
		if (rsp == null) {
			return this;
		}
		throw new ErrorRspException(rsp);
	}
}
