package com.zxk.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc = null;
	private static String realpath = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		realpath = config.getServletContext().getRealPath("");
		BeanFactoryContext.setWc(wc);
	}
	public static String getRealpath(){
		return realpath;
	}
	
	public static WebApplicationContext getwc(){
		return wc;
	}


}
