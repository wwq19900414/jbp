package com.thestore.eam.utils;
import java.util.ResourceBundle;


public class CurrentVersionUtil {

	private static String CURRENTVERSION = null;
	
	static {
		initCurrentVersion();
	}

	public static void flushCurrentVersion() {

	}

	public static void initCurrentVersion() {
		ResourceBundle rm = ResourceBundle.getBundle("codeversion");
		String currentversionStr = null;
		try{
			currentversionStr = rm.getString("currentversion");
		}catch(Exception e){
			currentversionStr = "0";
		}
		if(currentversionStr.trim().equals("")) {
			CURRENTVERSION = "";
		}else{
			CURRENTVERSION = currentversionStr;
		}
	}

	public static String getCurrentVersion() {
			return CURRENTVERSION;
	}
	
	public static void setCurrentVersion(String currentVersion) {
		CURRENTVERSION = currentVersion;
	}
	
}
