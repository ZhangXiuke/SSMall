package com.zxk.task;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.zxk.common.JsonUtils;
import com.zxk.common.PropertiesUtil;
import com.zxk.common.WechatContext;
import com.zxk.pojo.AccessTokenVo;

@Component
public class WechatTask {
	
	private static final String ASSESS_TOKEN = PropertiesUtil.getStringByKey("access_token", "wechat.properties");
	private static final String APPID = PropertiesUtil.getStringByKey("appid", "wechat.properties");
	private static final String APPSECRET = PropertiesUtil.getStringByKey("appsecret", "wechat.properties");
	
	public static final String at = "12_9d5bqXrOsbGv_cCZghwPz76gYq4JUdLVMjpvxlOtGGeboYTCSyAdMBuYazlGsNmPZUzpOdl6PcYEjZJO_M9KhUQTXyhADHTiACmEgDPwDWSKDBGbW60smPUU0rW5QtAunRJ_ZaIVPfSUTDNBGXZdAFAVMO";
	public void wechatAccessToken(){
		System.out.println("===================="+"refresh!");
		HttpGet get = null;
		CloseableHttpResponse resp = null;
		CloseableHttpClient client = null;
		try {
			client = HttpClients.createDefault();
			String url = ASSESS_TOKEN;
			url = url.replaceAll("APPID", APPID);
			url = url.replaceAll("APPSECRET", APPSECRET);
			get = new HttpGet(url);
			
			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode >= 200 && statusCode < 300){
				HttpEntity entity = resp.getEntity();
				String content = EntityUtils.toString(entity);
				try {
					AccessTokenVo at = JsonUtils.json2pojo(content, AccessTokenVo.class);
					//access_token = at.getAccess_token();
					WechatContext.setAssessToken(at.getAccess_token());
					System.out.println(at.getAccess_token()+","+at.getExpires_in());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(resp != null){
				try {
					resp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
