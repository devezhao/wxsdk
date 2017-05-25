package cn.devezhao.wxsdk.mp.metas;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Zhao Fangfang
 * @version $Id: RichSend.java 181 2016-05-20 11:22:01Z zhaoff@wisecrm.com $
 * @since 0.2, 2014-4-11
 */
public class RichSend extends BaseSend implements Rich {
	private static final long serialVersionUID = 5623156454871614458L;

	private List<String[]> articles = new LinkedList<String[]>();
	
	public RichSend(String touser) {
		super(touser, "news");
	}
	
	@Override
	public Rich addArticle(String title, String description, String picUrl, String url) {
		articles.add(new String[] { title, description, picUrl, url });
		return this;
	}
	
	@Override
	public String toJSON() {
		JSONObject json = buildJSON();
		
		JSONObject news = new JSONObject();
		json.put("news", news);
		JSONArray articles = new JSONArray();
		news.put("articles", articles);
		for (String[] a : this.articles) {
			JSONObject item = new JSONObject();
			item.put("title", a[0]);
			if (a[1] != null) {
				item.put("description", a[1]);
			}
			item.put("picurl", a[2]);
			
			// 不自动带openid
			String url = a[3];
			if (!url.contains("unopenid")) {
				url += "#openid=" + map.get("touser");
			}
			item.put("url", url);
			articles.add(item);
		}
		return json.toJSONString();
	}
}
