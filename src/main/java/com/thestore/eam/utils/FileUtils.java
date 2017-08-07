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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class FileUtils {
	public final static String FSPT = File.separator;

	/**
	 * @Title:
	 * @Description:
	 * @param
	 * @return void
	 * @author zhangxu1(zhangxu1@yihaodian.com)
	 * @date 2012-7-6
	 */
	public static void writeFile(String dirPath, String filePath, String content) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(filePath);
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(file, true);
			osw = new OutputStreamWriter(fos, "utf-8");
			osw.write(content);
			osw.flush();
			fos.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (osw != null)
					osw.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 删除指定目录下的所有XML文件
	 * 
	 * @Title: deleteFiles
	 * @Description:
	 * @param
	 * @return void
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date 2012-8-16
	 */
	public static void deleteFiles(String dirPath) {
		File dir = new File(dirPath);
		if (dir.exists() && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			for (File file : fileList) {
				if (file.getName().endsWith(".xml"))
					file.delete();
			}
		}
	}

	/**
	 * 
	 * @Title: copy
	 * @Description: 上传文件
	 * @param @param src 临时上传地址
	 * @param @param dest 目标上传地址
	 * @param @param size 文件限制大小
	 * @return void 返回类型
	 * @author
	 * @date 2011-10-24
	 * @throws
	 */
	public static void copy(File src, File dest, int size) {
		InputStream in = null;
		OutputStream out = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		byte[] buffer = new byte[size];

		if (!dest.exists()) {
			try {
				dest.createNewFile();
			} catch (IOException e) {
			}
		}

		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);
			try {
				in = new BufferedInputStream(fis);
				while (in.read(buffer) > 0) {
					try {
						out = new BufferedOutputStream(fos, size);
						out.write(buffer);

					} catch (IOException e) {
					} finally {
						if (out != null) {
							try {
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} catch (IOException e) {
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		} catch (FileNotFoundException e1) {
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static void download(String urlString, String filename, String savePath) {
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			System.out.println("下载图片出错");
			e.printStackTrace();
		}
	}
}
