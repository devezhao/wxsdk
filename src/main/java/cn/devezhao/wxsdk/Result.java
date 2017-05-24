package cn.devezhao.wxsdk;

/**
 * 接口返回结果
 * 
 * @author Zhao Fangfang
 * @version $Id: Result.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public interface Result {

	public String getString(String key);
	
	public Integer getInt(String key);
	
	public Long getLong(String key);
	
	public Double getDouble(String key);
	
	public ErrorRsp hasError();
	
	public Result throwIfError();
}
