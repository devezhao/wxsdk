package cn.devezhao.wxsdk.mp;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.qdss.commons.util.CodecUtils;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.ApiUtils;

/**
 * 获取媒体文件
 * 
 * @author Zhao Fangfang
 * @version $Id: MediaGet.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-12
 */
public class MediaGet extends BaseApi implements AuthzApi<MediaGet> {

	public MediaGet(ApiInvoker invoker) {
		super("http://file.api.weixin.qq.com/cgi-bin/media/get", invoker);
	}

	@Override
	public MediaGet access_token(String value) {
		return (MediaGet) addParam("access_token", value);
	}
	
	public MediaGet media_id(String value) {
		return (MediaGet) addParam("media_id", value);
	}
	
	public File execGet(File toDir) throws IOException {
		String uri = ApiUtils.toApiInvokeURI(getApiUrl(), getParams(), null);
		GetMethod method = new GetMethod(uri);
		method.getParams().setSoTimeout(1000 * 60 * 2);
		byte[] bytes = getApiInvoker().getHttpClientFetcher().readBinary(method);
		
		Header cd = method.getResponseHeader("Content-disposition");
		String fileName = cd == null ? "NOCD-" + CodecUtils.randomCode(32) : cd.getValue();
		if (cd != null) {
			fileName = fileName.split("filename=")[1];
			fileName = fileName.replaceAll("\"", "");
		}
		File toFile = new File(toDir, fileName);
		
		OutputStream os = null;
		try {
			os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(toFile)));
			os.write(bytes, 0, bytes.length);
			os.flush();
		} finally {
			IOUtils.closeQuietly(os);
		}
		return toFile;
	}
}
