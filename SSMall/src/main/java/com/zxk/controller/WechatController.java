package com.zxk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zxk.common.JsonUtils;
import com.zxk.common.PropertiesUtil;
import com.zxk.common.Sign;
import com.zxk.msg.MessageKit;
import com.zxk.pojo.AccessTokenVo;
import com.zxk.task.WechatTask;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	private static final String ASSESS_TOKEN = PropertiesUtil.getStringByKey("access_token", "wechat.properties");
	private static final String APPID = PropertiesUtil.getStringByKey("appid", "wechat.properties");
	private static final String APPSECRET = PropertiesUtil.getStringByKey("appsecret", "wechat.properties");
	
	public static final String TOKEN = "peterzxk";
	
	/**
	 * 连接微信
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/wget", method = RequestMethod.GET)
	public void init(HttpServletRequest request ,HttpServletResponse response){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String[] arrs = {WechatController.TOKEN,timestamp,nonce};
		Arrays.sort(arrs);
		StringBuffer sb = new StringBuffer();
		for(String s : arrs){
			sb.append(s);
		}
		
		String sha1 = Sign.SHA1(sb.toString());
		if(signature.equals(sha1)){
			try {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 获取token
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAccessToken")
	public String getAccessToken(HttpServletRequest request , HttpServletResponse response){
		String access_token = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			String url = ASSESS_TOKEN;
			url = url.replaceAll("APPID", APPID);
			url = url.replaceAll("APPSECRET", APPSECRET);
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse resp = null;
			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode >= 200 && statusCode < 300){
				HttpEntity entity = resp.getEntity();
				String content = EntityUtils.toString(entity);
				try {
					AccessTokenVo at = JsonUtils.json2pojo(content, AccessTokenVo.class);
					access_token = at.getAccess_token();
					System.out.println(at.getAccess_token()+","+at.getExpires_in());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return access_token;
	}
	
	/**
	 * 删除自定义菜单
	 * @param res
	 * @param rep
	 */
	@RequestMapping("/deleteMenu")
	public void deleteMenu(HttpServletRequest res , HttpServletResponse rep){
		CloseableHttpClient client = HttpClients.createDefault();
		String url = PropertiesUtil.getStringByKey("deleteMenu","wechat.properties");
		url = url.replaceAll("ACCESS_TOKEN", WechatTask.at);
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse resp = null;
		try {
			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			System.out.println("==========" +statusCode);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增自定义菜单
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addMenu")
	public void addMenu(HttpServletRequest request , HttpServletResponse response){
	
		String json = "{\"button\":[{\"type\":\"click\",\"name\":\"项目管理\",\"key\":\"20_PROMANAGE\"},{\"type\":\"click\",\"name\":\"机构运作\",\"key\":\"30_ORGANIZATION\"},{\"name\":\"日常工作\",\"sub_button\":[{\"type\":\"click\",\"name\":\"待办工单\",\"key\":\"01_WAITING\"},{\"type\":\"click\",\"name\":\"已办工单\",\"key\":\"02_FINISH\"},{\"type\":\"click\",\"name\":\"我的工单\",\"key\":\"03_MYJOB\"},{\"type\":\"click\",\"name\":\"公告消息箱\",\"key\":\"04_MESSAGEBOX\"},{\"type\":\"click\",\"name\":\"签到\",\"key\":\"05_SIGN\"}]}]}";
		try {
					
			CloseableHttpClient client = HttpClients.createDefault();
			
			String url = PropertiesUtil.getStringByKey("addMenu", "wechat.properties");
			url = url.replaceAll("ACCESS_TOKEN", WechatTask.at);
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type","application/json");
			StringEntity entity = new StringEntity(json,ContentType.create("application/json", "utf-8"));
			post.setEntity(entity);
			CloseableHttpResponse resp = client.execute(post);
			int status = resp.getStatusLine().getStatusCode();
			if(status >= 200 && status < 300){
				System.out.println("============"+ EntityUtils.toString(entity));
			}
			
		}  catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/wget",method = RequestMethod.POST)
	public void getInfo(HttpServletRequest request , HttpServletResponse response) throws IOException{
		/*BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String str = null;
		while((str = br.readLine()) != null){
			System.out.println(str);
		}*/
		Map<String,String > map = MessageKit.reqMeg2Map(request);
		System.out.println("===============" + map);
		String respCon = MessageKit.handlerMsg(map);
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(respCon);
		response.getWriter().write(respCon);
	}
}
