package com.zxk.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

public class UploadFile 
{
	public static final String FILE_NAME = PropertiesUtil.getStringByKey("fileName", "sysconproperties.properties");
	
	public static String UploadFile1(String fileData,String fType,HttpServletRequest request,
			HttpServletResponse response){ 
		if(Tools.isEmpty(fType)){
			fType = FILE_NAME;
		}
		String imgPath = request.getRealPath(fType);
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdir();
		}
		String fileName = "";
		try {
			fileName  = LocalMac.getLocalMac() + System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		imgPath = imgPath + "/" + fileName + ".jpg";
		
		file = new File(imgPath);  
		if(!file.exists()){
			file.mkdir();
		}
		 //使用BASE64对图片文件数据进行解码操作  
        BASE64Decoder decoder = new BASE64Decoder();  
        
        try {  
            //通过Base64解密，将图片数据解密成字节数组  
            byte[] bytes = decoder.decodeBuffer(fileData);  
            //构造字节数组输入流  
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);  
            //读取输入流的数据  
            BufferedImage bi = ImageIO.read(bais);  
            //将数据信息写进图片文件中  
            ImageIO.write(bi, "jpg", file);// 不管输出什么格式图片，此处不需改动  
            bais.close();  
        } catch (IOException e) { 
        	
        }  
        return fType +"/" + fileName + ".jpg";  
	}
}
