package cn.devezhao.wxsdk.wxx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.JsonResult;
import cn.devezhao.wxsdk.mp.AuthzApi;
import cn.devezhao.wxsdk.mp.BaseApi;
import cn.devezhao.wxsdk.utils.ApiUtils;

/**
 * 小程序码
 * 
 * @author zhaofang123@gmail.com
 * @since 01/16/2018
 */
public class GetwxacodeUnlimit extends BaseApi implements AuthzApi<GetwxacodeUnlimit> {

	private Map<String, Object> data = new HashMap<>();
	
	public GetwxacodeUnlimit(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/wxa/getwxacodeunlimit", invoker);
	}

	@Override
	public GetwxacodeUnlimit access_token(String value) {
		return (GetwxacodeUnlimit) addParam("access_token", value);
	}
	
	public GetwxacodeUnlimit page(String page) {
		data.put("page", page);
		return this;
	}
	
	public GetwxacodeUnlimit scene(String scene) {
		data.put("scene", scene);
		return this;
	}
	
	public GetwxacodeUnlimit width(int width) {
		data.put("width", width);
		return this;
	}
	
	@Override
	public String getPostJson() {
		data.put("auto_color", false);
		return JSON.toJSONString(data);
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public Object execBinary() throws IOException {
		String uri = ApiUtils.toApiInvokeURI(getApiUrl(), getParams(), null);
		
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new StringEntity(getPostJson(), ContentType.APPLICATION_JSON));
		HttpResponse resp = getApiInvoker().getHttpClient().getHttpClient().execute(httpPost);
		HttpEntity entity = resp.getEntity();
		if (entity.isStreaming()) {
			byte[] bs = EntityUtils.toByteArray(entity);
			return bs;
		} else {
			String r = EntityUtils.toString(entity, "utf-8");
			return new JsonResult(r, resp.getAllHeaders());
		}
	}
}
