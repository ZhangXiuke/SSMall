package com.zxk.common;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zxk.task.WechatTask;

public class WechatPost {

	/**
	 * 公共post发送发放
	 * @param url
	 * @param json
	 * @param type
	 * @return
	 */
	public static String weChatPost(String url,String json,String type){
		CloseableHttpClient client = null;
		CloseableHttpResponse resp = null;
		client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","applocation/json");
		
		StringEntity entity = new StringEntity(json, ContentType.create(type));
		post.setEntity(entity);
		try {
			resp = client.execute(post);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode >= 200 && statusCode < 300){
				String str = EntityUtils.toString(resp.getEntity());
				return str;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(resp != null){
				try {
					resp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null; 
	}
}
