package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 创建粉丝分组（标签）
 * 
 * @author Pengrl
 * @version $Id: GroupsCreate.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-7-24
 */
public class GroupsCreate extends BaseApi implements AuthzApi<GroupsCreate> {
	
	public GroupsCreate(ApiInvoker apiInvoker) {
		super("https://api.weixin.qq.com/cgi-bin/groups/create", apiInvoker);
	}

	@Override
	public GroupsCreate access_token(String value) {
		return (GroupsCreate) addParam("access_token", value);
	}

	public GroupsCreate group(String value) {
		return (GroupsCreate) setPostJson(value);
	}
}
