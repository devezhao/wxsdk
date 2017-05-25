package cn.devezhao.wxsdk.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

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
				uri.append(e.getKey()).append('=').append(urlEncode(e.getValue()));
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
			sha1 = toSHA1Hex(sb.toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return sha1.equals(map.get("signature"));
	}
	
	/**
	 * URL 编码
	 * 
	 * @param text
	 * @return
	 */
	public static String urlEncode(String text) {
		try {
			return URLEncoder.encode(text, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Exception encoding URL string: " + e);
		}
	}
	
	/**
	 * SHA1加密
	 * 
	 * @param input
	 * @return
	 */
	public static byte[] toSHA1(byte[] input) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		digest.update(input);
		return digest.digest();
	}
	
	/**
	 * SHA1加密
	 * 
	 * @param input
	 * @return
	 */
	public static String toSHA1Hex(byte[] input) {
		return toHexString(toSHA1(input));
	}
	
	/**
	 * @param data
	 * @return
	 */
	public static String toHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (byte b : data) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	/**
	 * @param xml
	 * @return
	 */
	public static Document parseDocument(String xml) {
		try {
			return DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			throw new RuntimeException(xml);
		}
	}
} 
