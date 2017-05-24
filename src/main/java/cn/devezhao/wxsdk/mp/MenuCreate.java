package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 发布菜单
 * 
 * @author Pengrl
 * @version $Id: MenuCreate.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-4-8
 */
public class MenuCreate extends BaseApi implements AuthzApi<MenuCreate> {

	public MenuCreate(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/menu/create", invoker);
	}

	@Override
	public MenuCreate access_token(String value) {
		return (MenuCreate) addParam("access_token", value);
	}
	
	public MenuCreate addButton(String jsonText) {
		return (MenuCreate) setPostJson(jsonText);
	}

}
