package com.zxk;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zxk.common.JsonUtils;
import com.zxk.common.PropertiesUtil;
import com.zxk.controller.WechatController;
import com.zxk.msg.MediaKit;
import com.zxk.msg.MessageKit;
import com.zxk.pojo.AccessTokenVo;
import com.zxk.pojo.ModelMsgDataVo;
import com.zxk.pojo.TmeplateMsg;

public class TestController {

	public static final String APP_ID = PropertiesUtil.getStringByKey("appid", "wechat.properties");

	@Test
	public void testsss(){
		String mid  = MediaKit.postMedia("C:/Users/win7/Desktop/001.jpg", "image");
		System.out.println(mid);
	}
	
	@Test	
	public void getTest(){
		MediaKit.getMedia("MsQ77JK5-8ji0UW_lCTqa1Uv4ylt4541AV_eJ29FTNGwUSrFRIrEKJlH-srbY_dV", new File("f:/aaa.jpg"));
	}
	
	@Test
	public void testPostTemplateMsg(){
		TmeplateMsg tl = new TmeplateMsg();
		Map<String,Object> data = new HashMap<String,Object>();
		tl.setTemplate_id("wx8mry9G_L3F4-xOpDpP5-zJ8Vf9O5xRxko9Jlpdh5Y");
		tl.setTouser("o-Z--0WAugMSDHMQFI1EhM4A8KB0");
		tl.setUrl("www.baidu.com");
		data.put("num", new ModelMsgDataVo("111","#00ff00"));
		tl.setData(data);
		try {
			System.out.println("====="+JsonUtils.obj2json(tl));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageKit.postTmeplate(tl);
	}
}
