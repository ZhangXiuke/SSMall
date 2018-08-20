package com.zxk.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class LocalMac {
	/**
	 * @param args
	 * @return 
	 * @throws Exception 
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
//	public static String main(String[] args) throws UnknownHostException, SocketException {
//		// TODO Auto-generated method stub
//		
//		//得到IP，输出PC-201309011313/122.206.73.83
//		
//		System.out.println(ia);
//		return getLocalMac(ia);
//	}
	public static String getLocalMac() throws Exception{
		String osname = System.getProperty("os.name").toLowerCase();
		String mac;
		if (osname.equals("windows 7")) {  
            mac = getMACAddress();  
        } else if (osname.startsWith("windows")) {  
            mac = getWindowsMACAddress();  
        } else {  
            mac = getUnixMACAddress();  
        }  
		return mac;
	}
	
    /** 
     * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取. 
     * 如果有特殊系统请继续扩充新的取mac地址方法. 
     *  
     * @return mac地址 
     */  
    public static String getUnixMACAddress() {  
        String mac = null;  
        BufferedReader bufferedReader = null;  
        Process process = null;  
        try {  
            // linux下的命令，一般取eth0作为本地主网卡  
            process = Runtime.getRuntime().exec("ifconfig eth0");  
            // 显示信息中包含有mac地址信息  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    process.getInputStream()));  
            String line = null;  
            int index = -1;  
            while ((line = bufferedReader.readLine()) != null) {  
                // 寻找标示字符串[hwaddr]  
                index = line.toLowerCase().indexOf("hwaddr");  
                if (index >= 0) {// 找到了  
                    // 取出mac地址并去除2边空格  
                    mac = line.substring(index + "hwaddr".length() + 1).trim();  
                    break;  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException e1) {  
                e1.printStackTrace();  
            }  
            bufferedReader = null;  
            process = null;  
        }  
        return mac;  
    }  
  
    /** 
     * 获取widnows网卡的mac地址. 
     *  
     * @return mac地址 
     */  
    public static String getWindowsMACAddress() {  
        String mac = null;  
        BufferedReader bufferedReader = null;  
        Process process = null;  
        try {  
            // windows下的命令，显示信息中包含有mac地址信息  
            process = Runtime.getRuntime().exec("ipconfig /all");  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    process.getInputStream()));  
            String line = null;  
            int index = -1;  
            while ((line = bufferedReader.readLine()) != null) {  
                System.out.println(line);  
                // 寻找标示字符串[physical  
                index = line.toLowerCase().indexOf("physical address");  
                  
                if (index >= 0) {// 找到了  
                    index = line.indexOf(":");// 寻找":"的位置  
                    if (index >= 0) {  
                        System.out.println(mac);  
                        // 取出mac地址并去除2边空格  
                        mac = line.substring(index + 1).trim();  
                    }  
                    break;  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException e1) {  
                e1.printStackTrace();  
            }  
            bufferedReader = null;  
            process = null;  
        }  
  
        return mac;  
    }  
  
    /** 
     * windows 7 专用 获取MAC地址 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String getMACAddress() throws Exception {  
          
        // 获取本地IP对象  
        InetAddress ia = InetAddress.getLocalHost();  
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  
  
        // 下面代码是把mac地址拼装成String  
        StringBuffer sb = new StringBuffer();  
  
        for (int i = 0; i < mac.length; i++) {  
            if (i != 0) {  
                sb.append("-");  
            }  
            // mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);  
            sb.append(s.length() == 1 ? 0 + s : s);  
        }  
  
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回  
        return sb.toString().toUpperCase();  
    }  
}