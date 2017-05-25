package cn.devezhao.wxsdk.mp;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.utils.ApiUtils;

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
		byte[] bytes = getApiInvoker().getHttpClient().readBinary(uri, 60 * 1000);
		
		String fileName = UUID.randomUUID().toString();
		File toFile = new File(toDir, fileName);
		
		OutputStream os = null;
		try {
			os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(toFile)));
			os.write(bytes, 0, bytes.length);
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
		return toFile;
	}
}
