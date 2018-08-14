package com.zxk.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zxk.msg.MediaKit;
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
}
