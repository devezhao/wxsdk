package cn.devezhao.wxsdk;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.qdss.commons.util.CodecUtils;
import org.qdss.commons.util.EncryptUtils;

/**
 * Api工具类
 * 
 * @author <a href="mailto:zhaofang123@gmail.com">FANGFANG ZHAO</a>
 * @since 0.1.0, Nov 17, 2009
 * @version $Id: ApiUtils.java 60 2015-08-26 09:21:51Z zhaoff@wisecrm.com $
 */
public class ApiUtils {

	/**
	 * 转换参数到符合调用规范的URI
	 * 
	 * @param url
	 * @param params
	 * @param notInUriParamName
	 * @return
	 */
	public static String toApiInvokeURI(String url, Map<String, String> params, String notInUriParamName) {
        StringBuffer uri = new StringBuffer(url);
		uri.append('?');
		boolean first = true;
		for (Map.Entry<String, String> e : params.entrySet()) {
			if (first) {
				first = false;
			} else {
				uri.append('&');
			}
			
			if (notInUriParamName != null && notInUriParamName.equalsIgnoreCase(e.getKey())) {
			} else {
				uri.append(e.getKey()).append('=').append(CodecUtils.urlEncode(e.getValue()));
			}
		}
		return uri.toString();
	}
	
	/**
	 * 验证消息推送合法性
	 * 
	 * @param selfToken
	 * @param reqUrl
	 * @return
	 */
	public static boolean verfiySign(String selfToken, String reqUrl) {
		String query = reqUrl.split("[?]")[1];
		Map<String, String> map = new HashMap<String, String>();
		for (String q : query.split("[&]")) {
			String kv[] = q.split("[=]");
			if (kv.length == 2) {
				map.put(kv[0], kv[1]);
			}
		}
		
		if (!map.containsKey("timestamp") || !map.containsKey("nonce") || !map.containsKey("signature")) {
			return false;
		}
		
		Set<String> tree = new TreeSet<String>();
		tree.add(selfToken);
		tree.add(map.get("timestamp"));
		tree.add(map.get("nonce"));
		StringBuffer sb = new StringBuffer();
		for (String t : tree) {
			sb.append(t);
		}
		
		String sha1 = "";
		try {
			sha1 = EncryptUtils.toSHA1Hex(sb.toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return sha1.equals(map.get("signature"));
	}
}
