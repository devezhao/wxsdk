package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 获取粉丝分组（标签）
 * 
 * @author Pengrl
 * @version $Id: GroupsGet.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-4-2
 */
public class GroupsGet extends BaseApi implements AuthzApi<GroupsGet> {

	public GroupsGet(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/groups/get", invoker);
	}

	@Override
	public GroupsGet access_token(String value) {
		return (GroupsGet) addParam("access_token", value);
	}
}
