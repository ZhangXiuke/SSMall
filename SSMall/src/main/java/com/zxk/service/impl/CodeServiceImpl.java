package com.zxk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxk.dao.CodeDao;
import com.zxk.pojo.CodeVo;

@Service("/codeService")
public class CodeServiceImpl implements CodeService{

	@Autowired
	private CodeDao codeDao;
	
	@Override
	public List<CodeVo> getCodes() {
		
		return codeDao.getCodes();
	}

	
}
