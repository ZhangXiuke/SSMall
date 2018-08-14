package com.zxk.msg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zxk.common.JsonUtils;
import com.zxk.common.PropertiesUtil;
import com.zxk.pojo.WeixinMedia;
import com.zxk.task.WechatTask;

public class MediaKit {
	
	public static final String POST_MEDIA = PropertiesUtil.getStringByKey("post_media", "wechat.properties");
	public static final String GTE_MEDIA = PropertiesUtil.getStringByKey("get_media", "wechat.properties");
	
	public static String postMedia(String path,String type) {
		CloseableHttpClient client = null;
		CloseableHttpResponse resp = null;
		
		try {
			client = HttpClients.createDefault();
			String url = POST_MEDIA;
			url = url.replace("ACCESS_TOKEN", WechatTask.at);
			url = url.replace("TYPE", type);
			HttpPost post = new HttpPost(url);
			FileBody fb = new FileBody(new File(path));
			HttpEntity reqEntity = MultipartEntityBuilder
						.create().addPart("media", fb).build();
			post.setEntity(reqEntity);
			resp = client.execute(post);
			int sc = resp.getStatusLine().getStatusCode();
			if(sc>=200&&sc<300) {
				String json = EntityUtils.toString(resp.getEntity());
				WeixinMedia wm = null;
				try {
					wm = JsonUtils.json2pojo(json, WeixinMedia.class);
					return wm.getMedia_id();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(client!=null) client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(resp!=null) resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void getMedia(String mediaId,File f) {
		CloseableHttpClient client = null;
		CloseableHttpResponse resp = null;
		
		try {
			client = HttpClients.createDefault();
			String url = GTE_MEDIA;
			url = url.replace("ACCESS_TOKEN", WechatTask.at);
			url = url.replace("MEDIA_ID", mediaId);
			HttpGet get = new HttpGet(url);
			resp = client.execute(get);
			int sc = resp.getStatusLine().getStatusCode();
			if(sc>=200&&sc<300) {
				InputStream is = resp.getEntity().getContent();
				FileUtils.copyInputStreamToFile(is, f);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(client!=null) client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(resp!=null) resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
