package cn.devezhao.wxsdk;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * API 调用失败异常
 * 
 * @author <a href="mailto:zhaofang123@gmail.com">FANGFANG ZHAO</a>
 * @since 0.1.0, Nov 17, 2009
 * @version $Id: ApiException.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 */
public class ApiException extends NestableRuntimeException {
	private static final long serialVersionUID = 8731618228498904557L;

	public ApiException() {
		super();
	}

	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(Throwable cause) {
		super(cause);
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
