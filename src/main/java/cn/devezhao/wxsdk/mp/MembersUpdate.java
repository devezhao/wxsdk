package cn.devezhao.wxsdk.mp;

import cn.devezhao.wxsdk.ApiInvoker;

/**
 * 更新分组（标签）成员
 * 
 * @author Pengrl
 * @version $Id: MembersUpdate.java 17 2015-07-16 09:38:17Z zhaoff@wisecrm.com $
 * @since 1.1, 2014-4-11
 */
public class MembersUpdate extends BaseApi implements AuthzApi<MembersUpdate> {

	public MembersUpdate(ApiInvoker invoker) {
		super("https://api.weixin.qq.com/cgi-bin/groups/members/update", invoker);
	}

	@Override
	public MembersUpdate access_token(String value) {
		return (MembersUpdate) addParam("access_token", value);
	}

	/**
	 * {"openid":"openid","to_groupid":"groupid"}
	 * @param jsonText
	 * @return
	 */
	public MembersUpdate to_group_json(String jsonText) {
		return (MembersUpdate) setPostJson(jsonText);
	}
}
