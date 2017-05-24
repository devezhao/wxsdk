package cn.devezhao.wxsdk;

/**
 * 
 * @author Zhao Fangfang
 * @version $Id: ErrorRspException.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-19
 */
public class ErrorRspException extends ApiException {
	private static final long serialVersionUID = -6992499445620037556L;

	private ErrorRsp errorRsp;
	
	public ErrorRspException(ErrorRsp rsp) {
		super(rsp.toString());
		this.errorRsp = rsp;
	}
	
	public ErrorRsp getErrorRsp() {
		return errorRsp;
	}
}
