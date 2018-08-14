package com.zxk.common;

public class WechatContext {

	private static String acessToken;
	
	public static void setAssessToken(String acessToken){
		WechatContext.acessToken =  acessToken;
	}
	
	public static String getAccessToken(){
		return WechatContext.acessToken;
	}
}
