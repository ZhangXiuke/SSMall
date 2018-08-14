package com.zxk.pojo;

public class ModelMsgDataVo {

	private String color;
	private String value;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ModelMsgDataVo(String value,String color) {
		// TODO Auto-generated constructor stub
		this.color = color;
		this.value = value;
	}

}
