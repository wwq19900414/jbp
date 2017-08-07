package com.thestore.eam.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 定时任务开关相关的操作类
 *
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2013-7-15
 */
public class CronTaskUtils {
	private static Log log = LogFactory.getLog(CronTaskUtils.class);
	
	/**
	 * 根据配置文件taskSwitch.properties中的flag变量的值:1:开启，2:关闭<br>
	 * 来判断定时任务是不是开启了,开启了返回true,否则返回false
	 * @Title: isTaskOpen
	 * @Description:
	 * @param 
	 * @return boolean
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-7-15
	 */
	public static boolean isTaskOpen(){
		String flag = "2";
		String filePath = "";
		
		//物理读取配置文件taskSwitch.properties中定时任务开关flag
		String dir = CronTaskUtils.class.getResource("/").toString().substring(5);//获取classes文件夹的绝对路径,将前面的file:前缀去掉
		filePath = dir + "taskSwitch.properties";//前面加‘/’才能在linux服务器上顺利读取到此配置文件,且兼容windows系统
		Properties prop = new Properties();//属性集合对象    
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			flag = prop.getProperty("flag");
			
			
			if (flag.equals("1")) {//flag=1代表定时任务处于开启状态
				return true;
			}else{//其他情况代表定时任务处于关闭状态
				return false;
			}
		} catch (Exception e) {//读取配置文件异常时视为定时任务处于关闭状态
			log.error("the path of taskSwitch.properties file error!!!",e);
			return false;
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					log.error("close file stream failed!!!",e);
				}
			}
		}
	}
	
	
	/**
	 * 设置定时任务的开关状态(即物理写taskSwitch.properties文件中的flag的值)<br>
	 * 返回是否成功完成设置开关的任务,顺利完成设置返回true,设置没有完成返回false
	 * @Title: setSwitch
	 * @Description:
	 * @param 
	 * @return void
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-7-15
	 */
	public static boolean setSwitch(int status){
		String filePath = "";
		
		//物理读取配置文件taskSwitch.properties中定时任务开关flag
		String dir = CronTaskUtils.class.getResource("/").toString().substring(5);//获取classes文件夹的绝对路径,将前面的file:前缀去掉
		filePath = dir + "taskSwitch.properties";//前面加‘/’才能在linux服务器上顺利读取到此配置文件,且兼容windows系统
	    
		
		Properties prop = new Properties();//属性集合对象    
	    prop.setProperty("flag", ""+status);
	    FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			prop.store(fos, "设置定时任务开关");
			return true;
		} catch (Exception e) {
			log.error("the path of taskSwitch.properties file error!!!",e);
			return false;
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					log.error("close file stream failed!!!",e);
				}
			}
			
		}
	}
	
	
	
	
	/**
	 * 根据配置文件taskSwitch.properties中的packageFlag变量的值:1:已经打包，2:还未打包<br>
	 * 来判断定时任务是不是开启了,已经打包true,否则返回false
	 * @Title: isTaskOpen
	 * @Description:
	 * @param 
	 * @return boolean
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-7-15
	 */
	public static boolean isPackage(){
		String flag = "2";
		String filePath = "";
		
		//物理读取配置文件taskSwitch.properties中定时任务开关flag
		String dir = CronTaskUtils.class.getResource("/").toString().substring(5);//获取classes文件夹的绝对路径,将前面的file:前缀去掉
		filePath = dir + "packageSwitch.properties";//前面加‘/’才能在linux服务器上顺利读取到此配置文件,且兼容windows系统
		Properties prop = new Properties();//属性集合对象    
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			flag = prop.getProperty("packageFlag");
			
			
			if (flag.equals("1")) {//pakageFlag=1代表已经打包
				return true;
			}else{//其他情况视为还未打包
				return false;
			}
		} catch (Exception e) {//读取配置文件异常时视为定时任务处于关闭状态
			log.error("the path of taskSwitch.properties file error!!!",e);
			return false;
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					log.error("close file stream failed!!!",e);
				}
			}
		}
	}
	
	
	/**
	 * 设置trackinfo数据打包状态(即物理写taskSwitch.properties文件中的packageFlag的值)<br>
	 * 设置status=1标识已经打包,status=2标识还未打包<br>
	 * 返回是否成功完成设置开关的任务,顺利完成设置返回true,设置没有完成返回false<br>
	 * @Title: setSwitch
	 * @Description:
	 * @param 
	 * @return void
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-7-15
	 */
	public static boolean setPackageStatus(int status){
		String filePath = "";
		
		//物理读取配置文件taskSwitch.properties中定时任务开关flag
		String dir = CronTaskUtils.class.getResource("/").toString().substring(5);//获取classes文件夹的绝对路径,将前面的file:前缀去掉
		filePath = dir + "packageSwitch.properties";//前面加‘/’才能在linux服务器上顺利读取到此配置文件,且兼容windows系统
	    
		
		Properties prop = new Properties();//属性集合对象    
	    prop.setProperty("packageFlag", ""+status);
	    FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			prop.store(fos, "设置trackInfo数据打包的状态");
			return true;
		} catch (Exception e) {
			log.error("the path of taskSwitch.properties file error!!!",e);
			return false;
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					log.error("close file stream failed!!!",e);
				}
			}
			
		}
	}
	
	
	
	/**
	 * 根据配置文件taskSwitch.properties中的googleFeedFlag变量的值:1:仅自营，2:全部<br>
	 * 来判断是否要收集一号店自营的商品feed，还是所有商品的feed<br>
	 * 返回true标识仅一号店自营商品，false标识一号店全站商品
	 * @Title: isTaskOpen
	 * @return boolean
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-7-15
	 */
	public static boolean isYihaodian(){
		String flag = "1";
		String filePath = "";
		
		//物理读取配置文件taskSwitch.properties中定时任务开关flag
		String dir = CronTaskUtils.class.getResource("/").toString().substring(5);//获取classes文件夹的绝对路径,将前面的file:前缀去掉
		filePath = dir + "googleFeed.properties";//前面加‘/’才能在linux服务器上顺利读取到此配置文件,且兼容windows系统
		Properties prop = new Properties();//属性集合对象    
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			flag = prop.getProperty("googleFeedFlag");
			
			
			if (flag.equals("1")) {//googleFeedFlag=1代表仅自营商品feed
				return true;
			}else{//其他情况视为全站商品feed
				return false;
			}
		} catch (Exception e) {//读取配置文件异常时视为定时任务处于关闭状态
			log.error("the path of googleFeed.properties file error!!!",e);
			return false;
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					log.error("close file stream failed!!!",e);
				}
			}
		}
	}
	
	
	/**
	 * 设置googleFeed数据包含的数据<br>
	 * 设置status=1标识取自营商品数据,status=2标识取全站商品数据<br>
	 * 返回是否成功完成设置开关的任务,顺利完成设置返回true,设置没有完成返回false<br>
	 * @Title: setSwitch
	 * @Description:
	 * @param 
	 * @return void
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date  2013-7-15
	 */
	public static boolean setGoogleFeedStatus(int status){
		String filePath = "";
		
		//物理读取配置文件taskSwitch.properties中定时任务开关flag
		String dir = CronTaskUtils.class.getResource("/").toString().substring(5);//获取classes文件夹的绝对路径,将前面的file:前缀去掉
		filePath = dir + "googleFeed.properties";//前面加‘/’才能在linux服务器上顺利读取到此配置文件,且兼容windows系统
	    
		
		Properties prop = new Properties();//属性集合对象    
	    prop.setProperty("googleFeedFlag", ""+status);
	    FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			prop.store(fos, "设置feed数据范围");
			return true;
		} catch (Exception e) {
			log.error("the path of googleFeed.properties file error!!!",e);
			return false;
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					log.error("close file stream failed!!!",e);
				}
			}
			
		}
	}
	
}
