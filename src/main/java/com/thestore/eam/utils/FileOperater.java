package com.thestore.eam.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class FileOperater
{
  public static String GetNewFileName(String oldFileName)
  {
    int pos = oldFileName.indexOf(".");
    String extendName = oldFileName.substring(pos);
    Calendar now = Calendar.getInstance();
    String newFileName = now.get(1) + "_" + 
      (now.get(2) + 1) + "_" + 
      now.get(5) + "_" + 
      now.get(11) + "_" + 
      now.get(12) + "_" + now.get(13) + 
      "_" + now.get(14) + "_" + Math.random() + 
      extendName;
    return newFileName;
  }

  public static String GetNewFileNames(String oldFileName)
  {
    Calendar now = Calendar.getInstance();
    String newFileName = now.get(1) + "_" + 
      (now.get(2) + 1) + "_" + 
      now.get(5) + "_" + 
      now.get(11) + "_" + 
      now.get(12) + "_" + now.get(13) + 
      "_" + now.get(14) + "_" + Math.random() + 
      oldFileName;
    return newFileName;
  }

  public static void deleteFile(String realFilePath)
  {
    try
    {
      File file = new File(realFilePath);
      while (file.exists())
        file.delete();
      System.out.println("============================文件已删除!");
    } catch (Exception e) {
      System.out.println("============================文件不存在或" + 
        e.getMessage());
    }
  }

  public static void UploadFile(File uploadFile, String realFilePath) throws IOException{
//	  if ( uploadFile != null ) {
//	      FileOutputStream out = new FileOutputStream(realFilePath);
//	      FileInputStream in = new FileInputStream(uploadFile);
//	      byte[] buffer = new byte[8192];
//	      int len = 0;
//	      while ( (len = in.read(buffer)) > 0 ) {
//	        out.write(buffer, 0, len);
//	      }
//	      in.close();
//		  out.close();
//	      
//	  }
	  
	  if ( uploadFile != null ) {
		  FileOutputStream out = null;
	      FileInputStream in = null;
		  try{
		      out = new FileOutputStream(realFilePath);
		      in = new FileInputStream(uploadFile);
		      byte[] buffer = new byte[8192];
		      int len = 0;
		      while ( (len = in.read(buffer)) > 0 ) {
		        out.write(buffer, 0, len);
		      }
		  }catch(IOException e){
			  throw e;
		  }finally{
			  try{
				  if(in != null){
					  in.close();
				  }
			  }catch(IOException e){
				  e.printStackTrace();
			  }
			  try{
				  if(out != null){
					  out.close();
				  }
			  }catch(IOException e){
				  e.printStackTrace();
				  throw e;
			  }
		  }
	      
	      
	  }
  }
  

  public static long getFileSize(File uploadFile) throws IOException {
//	  if (uploadFile != null) {
//	    FileInputStream in = new FileInputStream(uploadFile);
//	    long size = in.available();
//	    in.close();
//	    return size;
//	  }else{
//		  return 0;
//	  }
	  
	  if ( uploadFile != null ) {
		  FileInputStream in = null;
		  try{
			  in = new FileInputStream(uploadFile);
			  long size = in.available();
			  return size;
		  }catch(Exception e){
			  throw new IOException(e);
		  }finally{
			  try{
				  if(in != null){
					  in.close();
				  }
			  }catch(IOException e){
				  throw new IOException(e);
			  }
		  }
	  }else{
		  return 0;
	  }
}
  
  public static boolean downloadFile(String realPath, OutputStream os)
    throws FileNotFoundException
  {
    FileInputStream fis = new FileInputStream(realPath);
    return streamCopy(fis, os);
  }

  public static boolean streamCopy(InputStream source, OutputStream target) {
    boolean retValue = true;

    if ((target != null) && (source != null)) {
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      try
      {
        bis = new BufferedInputStream(source);
        bos = new BufferedOutputStream(target);

        byte[] buffer = new byte[1024];
        int c = -1;
        while ((c = bis.read(buffer, 0, buffer.length)) != -1) {
          bos.write(buffer, 0, c);
        }

        retValue = true;
      } catch (Exception localException) {
        return false;
      } finally {
        if (bis != null) {
          try {
            bis.close();
          } catch (IOException e) {
            retValue = false;
          }
        }
        if (bos != null) {
          try {
            bos.close();
          } catch (IOException e) {
            retValue = false;
          }
        }
      }
    }
    return retValue;
  }

  public static void fileDownLoad(HttpServletResponse response, String downpath)
  {
    String fileName = downpath.substring(downpath.lastIndexOf("\\") + 1);
    try {
      fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
    }
    downpath = downpath.replaceAll("/", "//");
    ServletOutputStream out = null;
    File file = null;
    FileInputStream fis = null;
    InputStream buf = null;
    try {
      out = response.getOutputStream();
      response.setHeader("Expires", "0");
      response.setHeader("Cache-Control", 
        "must-revalidate, post-check=0, pre-check=0");
      response.setHeader("Pragma", "public");
      response.setContentType("application/x-download");
      response.addHeader("Content-Disposition", "attachment;filename=\"" + 
        fileName + "\"");
      file = new File(downpath);
      fis = new FileInputStream(file);
      buf = new BufferedInputStream(fis);
      byte[] buffer = new byte[1];
      while (-1 != buf.read(buffer)) {
        out.write(buffer);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
    	if (buf != null) {
      	  try{
  	    	  buf.close();
  	      }catch(IOException e){
  	    	  
  	      }
        }
        if (fis != null) {
      	  try{
  		      fis.close();
  	      }catch(IOException e){
  	    	  
  	      }
        }
        if (out != null) {
      	  try{
      		  out.flush();
      	      out.close();
  	      }catch(IOException e){
  	    	  
  	      }
        }
    }
  }

  public static void fileDownLoadBy1B(HttpServletResponse response, String downpath)
  {
    String fileName = downpath.substring(downpath.lastIndexOf("\\") + 1);
    downpath = downpath.replaceAll("/", "//");
    ServletOutputStream out = null;
    File file = null;
    FileInputStream fis = null;
    InputStream buf = null;
    try {
      out = response.getOutputStream();
      response.setHeader("Expires", "0");
      response.setHeader("Cache-Control", 
        "must-revalidate, post-check=0, pre-check=0");
      response.setHeader("Pragma", "public");
      response.setContentType("application/x-download");
      response.addHeader("Content-Disposition", "attachment;filename=\"" + 
        fileName + "\"");
      file = new File(downpath);
      fis = new FileInputStream(file);
      buf = new BufferedInputStream(fis);
      byte[] buffer = new byte[1];
      while (-1 != buf.read(buffer)) {
        out.write(buffer);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
    	if (buf != null) {
      	  try{
  	    	  buf.close();
  	      }catch(IOException e){
  	    	  
  	      }
        }
        if (fis != null) {
      	  try{
  		      fis.close();
  	      }catch(IOException e){
  	    	  
  	      }
        }
        if (out != null) {
      	  try{
      		  out.flush();
      	      out.close();
  	      }catch(IOException e){
  	    	  
  	      }
        }
    }
  }
}