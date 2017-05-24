package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 删除菜单
 * 
 * @author Pengrl
 * @version $Id: MenuDelete.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-5-5
 */
public class MenuDelete extends BaseApi implements AuthzApi<MenuDelete> {

	public MenuDelete(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/menu/delete", invoker);
	}

	@Override
	public MenuDelete access_token(String value) {
		return (MenuDelete) addParam("access_token", value);
	}

}
