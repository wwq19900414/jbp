/**
 * Copyright (C), 2011-2016 The Store
 * File Name: Constants.java
 * Encoding: UTF-8
 * Date: 2013-6-6
 * History: 
 */
package com.thestore.eam.common;

import java.util.ResourceBundle;

/**
 * 媒体采购相关信息
 * 
 * @author zhangping(zhangping2@gmail.com)
 * @version Revision: 1.00 Date: 2013-6-6
 */
public class Constants {
	public final static int ADD = 1;
	public final static int EDIT = 2;
	public final static String DEFAULT_USER = "Administrator";

	/**
	 * 上传文件存放的总目录
	 */
	public final static String UPLOAD_PATH;

	/**
	 * 自动化创意上传文件存放的目录
	 */
	public final static String AUTO_CREATIVE_UPLOAD_PATH;

	/**
	 * 自动化创意模板需要包含的标记
	 */
	public final static String YHDAD_OUT_IMG = "YHDAD-OUT-IMG";// 产品图片
	public final static String YHDAD_OUT_PNAME = "YHDAD-OUT-PNAME";// 产品名称：
	public final static String YHDAD_OUT_PRICE = "YHDAD-OUT-PRICE"; // 产品价格
	public final static String YHDAD_OUT_LANDING = "YHDAD-OUT-LANDING";// 跳转链接（落地页）

	static {
		ResourceBundle rs = ResourceBundle.getBundle("path");
		UPLOAD_PATH = rs.getString("upload_path");
		AUTO_CREATIVE_UPLOAD_PATH = rs.getString("auto_creative_upload_path");
	}

	/** 缺失必须的信息 */
	public final static int NO_NECESSARY = 100;

	/** 重复 */
	public final static int IS_DUPLICATE = 101;

	/** 无效的值 */
	public final static int INVALID_VALUE = 103;

	/** tracker_u重复 */
	public final static int TRACKER_U_DULPLICATE = 102;

	/** 成功 */
	public final static int SUCCESS = 200;

	/** SQL报错 */
	public final static int SQL_EXCEPTION = 404;

	/** 已经不存在了 */
	public final static int NOT_EXIST = 503;

	// DMP标签
	public static final int LABEL_IS_COMMON = 1;// 常用
	public static final int LABEL_IS_NOTCOMMON = 0;
	public static final int LABEL_IS_LABEL_CATEGORY = 0;
	public static final int LABEL_IS_LABEL = 1;

	public static final int LABEL_UP_RANKING = 0;
	public static final int LABEL_DOWN_RANKING = 1;

}
