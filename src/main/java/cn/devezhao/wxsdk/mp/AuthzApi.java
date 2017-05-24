package cn.devezhao.wxsdk.mp;

/**
 * 需要授权（access_token）的API
 * 
 * @author Zhao Fangfang
 * @version $Id: AuthzApi.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-1
 */
public interface AuthzApi<T> {

	T access_token(String value);
}
