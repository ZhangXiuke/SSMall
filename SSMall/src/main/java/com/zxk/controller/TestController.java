package com.zxk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zxk.common.UploadFile;
import com.zxk.pojo.CodeVo;
import com.zxk.service.impl.CodeService;


@Controller
@RequestMapping("/hello")
public class TestController {
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping("/sayHello")
	public ModelAndView sayHello(){
		ModelAndView mo = new ModelAndView("/hello");
		String str = "Hello World~!";
		mo.addObject("message", str);
		return mo;
	}
	
	/**
	 * 获取全部编码
	 * @return
	 */
	@RequestMapping("/getCodes")
	public ModelAndView getCodes(){
		ModelAndView mo = new ModelAndView();
		List<CodeVo> codes = codeService.getCodes();
		for(CodeVo cc : codes){
			System.out.println("==============>" + cc.getName());
		}
		return mo;
	}
	
	/**
	 * 测试上传图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(HttpServletRequest request,
			HttpServletResponse response){
		String filename = request.getParameter("licenceName");

		String fileData = request.getParameter("fileData");// Base64编码过的图片数据信息，对字节数组字符串进行Base64解码
		String filePath = UploadFile.UploadFile1(fileData, filename, request, response);
		
		return filePath;
	}
}
