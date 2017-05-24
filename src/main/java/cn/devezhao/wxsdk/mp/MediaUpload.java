package cn.devezhao.wxsdk.mp;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import cn.devezhao.wxsdk.ApiException;
import cn.devezhao.wxsdk.ApiInvoker;
import cn.devezhao.wxsdk.ApiUtils;
import cn.devezhao.wxsdk.JsonResult;
import cn.devezhao.wxsdk.Result;

/**
 * 上传媒体文件
 * 
 * @author Zhao Fangfang
 * @version $Id: MediaUpload.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-12
 */
public class MediaUpload extends BaseApi implements AuthzApi<MediaUpload> {

	private File media;
	private String type;

	public MediaUpload(ApiInvoker invoker) {
		super("http://file.api.weixin.qq.com/cgi-bin/media/upload", invoker);
	}

	@Override
	public MediaUpload access_token(String value) {
		return (MediaUpload) addParam("access_token", value);
	}

	public MediaUpload type(String value) {
		this.type = value;
		return (MediaUpload) addParam("type", value);
	}

	public MediaUpload media(File value) {
		this.media = value;
		return this;
	}

	/**
	 * 上传媒体文件
	 * 
	 * @return
	 * @throws IOException
	 */
	public Result execUpload() throws IOException {
		if (media == null || !media.exists()) {
			throw new ApiException("the require params media is illegal");
		}
		if (this.type.equals("image")) {
			this.media = imgFomart(media);
		}

		URL urlObj = new URL(ApiUtils.toApiInvokeURI(getApiUrl(), getParams(), null));
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + media.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(media));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		String result = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			throw new IOException("发送POST请求出现异常", e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		return new JsonResult(result, null);
	}
	
	static final String JPG = "jpeg";
	public File imgFomart(File imgFile) throws IOException {
		String fileName = imgFile.getName();
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!suffix.equalsIgnoreCase("png") && !suffix.equalsIgnoreCase("bmp") && !suffix.equalsIgnoreCase("gif")) {
			return imgFile;
		}

		File newImg = new File(imgFile.getParent(), name + ".jpg");
		BufferedImage bIMG = ImageIO.read(imgFile);
		ImageIO.write(bIMG, JPG, newImg);
		return newImg;
    }
}
