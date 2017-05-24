package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 获取粉丝所在分组
 * 
 * @author Pengrl
 * @version $Id: GroupsGetId.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @param <T>
 * @since 1.1, 2014-4-2
 */
public class GroupsGetId extends BaseApi implements AuthzApi<GroupsGetId> {

	public GroupsGetId(ApiInvoker apiInvoker) {
		super("https://api.weixin.qq.com/cgi-bin/groups/getid", apiInvoker);
	}

	@Override
	public GroupsGetId access_token(String value) {
		return (GroupsGetId) addParam("access_token", value);
	}
	
	public GroupsGetId openid(String value) {
		return (GroupsGetId) setPostJson(value);
	}
}
