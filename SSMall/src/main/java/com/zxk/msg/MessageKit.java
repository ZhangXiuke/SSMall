package com.zxk.msg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
























import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.zxk.common.JsonUtils;
import com.zxk.common.PropertiesUtil;
import com.zxk.common.WechatPost;
import com.zxk.pojo.TmeplateMsg;
import com.zxk.task.WechatTask;

public class MessageKit {
	
	private static Map<String,String> replyMsgs = new HashMap<String,String>();
	static{
		replyMsgs.put("123", "你输入了123");
		replyMsgs.put("hello", "world");
		replyMsgs.put("run", "祝你一路平安!");
	}
	
	public static final String MESSAGE_TEXT_TYPE = PropertiesUtil.getStringByKey("msg_text_type", "wechat.properties");
	public static final String MESSAGE_EVENT_TYPE = PropertiesUtil.getStringByKey("msg_event_type", "wechat.properties");
	public static final String MESSAGE_IMAGE_TYPE = PropertiesUtil.getStringByKey("msg_image_type", "wechat.properties");
	public static final String MESSAGE_VOICE_TYPE = PropertiesUtil.getStringByKey("msg_voice_type", "wechat.properties");
	public static final String MESSAGE_LOCATION_TYPE = PropertiesUtil.getStringByKey("msg_location_type", "wechat.properties");
	public static final String MESSAGE_SHORTVIDEO_TYPE = PropertiesUtil.getStringByKey("msg_shortvideo_type", "wechat.properties");
	public static final String SEND_TMEPLATE = PropertiesUtil.getStringByKey("send_tmeplate", "wechat.properties");

	@SuppressWarnings("unchecked")
	public static Map<String,String> reqMeg2Map(HttpServletRequest request) throws IOException{
			String xml = reqMsg2xml(request);
			Map<String,String> maps = new HashMap<String, String>();
			try {
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();
				List<Element> eles = root.elements();
				for(Element e:eles) {
					maps.put(e.getName(), e.getTextTrim());
				}
				return maps;
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		return null;
	}

	/**
	 * httpServletRequest转xml方法
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String reqMsg2xml(HttpServletRequest request) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine()) != null){
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * 统一处理回复方法
	 * @param msgMap
	 * @return
	 * @throws IOException
	 */
	public static String handlerMsg(Map<String, String> msgMap) throws IOException {
		String msgType = msgMap.get("MsgType");
		if(msgType.equals(MESSAGE_EVENT_TYPE)){
			
		} else if(msgType.equals(MESSAGE_TEXT_TYPE)) {
			return textTypeHandler(msgMap);
		} else if(msgType.equals(MESSAGE_IMAGE_TYPE)) {
			return imageTypeHandler(msgMap,"MsQ77JK5-8ji0UW_lCTqa1Uv4ylt4541AV_eJ29FTNGwUSrFRIrEKJlH-srbY_dV");
		}
		return null;
	}

	/**
	 * 处理图片信息
	 * @param msgMap
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	private static String imageTypeHandler(Map<String, String> msgMap,
			String mediaId) throws IOException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ToUserName", msgMap.get("FromUserName"));
		map.put("FromUserName", msgMap.get("ToUserName"));
		map.put("CreateTime", new Date().getTime()+"");
		map.put("MsgType", "image");
		map.put("Image", "<MediaId>"+mediaId+"</MediaId>");
		return map2xml(map);
	}

	/**
	 * 处理文本消息
	 * @param msgMap
	 * @return
	 * @throws IOException
	 */
	private static String textTypeHandler(Map<String, String> msgMap) throws IOException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ToUserName", msgMap.get("FromUserName"));
		map.put("FromUserName", msgMap.get("ToUserName"));
		map.put("CreateTime", new Date().getTime()+"");
		map.put("MsgType", "text");
		String replyContent = "你请求的消息的内容不正确!";
		String con = msgMap.get("Content");
		if(replyMsgs.containsKey(con)) {
			replyContent = replyMsgs.get(con);
		}
		map.put("Content", replyContent);
		return map2xml(map);
	}
	/**
	 * mao2xml方法
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static String map2xml(Map<String, String> map) throws IOException {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("xml");
		Set<String> keys = map.keySet();
		for(String key:keys) {
			root.addElement(key).addText(map.get(key));
		}
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw);
		xw.setEscapeText(false);
		xw.write(d);
		return sw.toString();
	}
	
	/**
	 * 群发模板消息
	 * @param tmeplateMsg
	 * @return
	 */
	public static String postTmeplate(TmeplateMsg tmeplateMsg){

		String url = SEND_TMEPLATE;
		url = url.replaceAll("ACCESS_TOKEN", WechatTask.at);
		String json = null;
		try {
			json = JsonUtils.obj2json(tmeplateMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WechatPost.weChatPost(url, json, "applocation/json");
	}
}
