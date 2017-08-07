package com.thestore.eam.web.action;

import java.io.File;
import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.codehaus.plexus.util.StringUtils;

import com.thestore.eam.service.ApiManagerService;
import com.thestore.eam.utils.Encrypter;

/**
 * api后台管理Action
 * 
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2013-2-23
 */
@Namespace("/service/analysis/manager")
@Results({ @Result(name = "main", location = "/WEB-INF/backdoor.jsp"),
		@Result(name = "login", location = "/WEB-INF/back-login.jsp"),
		@Result(name = "doc", location = "/WEB-INF/doc.jsp"),
		@Result(name = "listFile", location = "/WEB-INF/listFile.jsp") })
public class ApiManagerAction extends BaseAction {
	private static final long serialVersionUID = -6611470632799883531L;

	private ApiManagerService apiManagerService;

	// 手动执行定时任务时传参数用的
	private Date date;
	private Date endDate;
	private Integer type;// 1:pageView,2:cartInfo,3:orderInfo,4:orderProduct,5:trackInfo
	private String agentId;

	private String trackUs;

	private String tableName;

	private String filename;

	private File upload;
	private String uploadFileName;
	private String uploadFileContentType;

	private String operateCode;
	private final String MD5_PASSWORD = "8aee4f444ba5e4b7431a16f82af416";
	private String sql;

	public boolean executeSql() {
		if (StringUtils.isEmpty(operateCode)) {
			return false;
		} else if (Encrypter.MD5Encrypt(operateCode).equals(MD5_PASSWORD)) {
			apiManagerService.executeSql(sql);
			return true;
		} else {
			return false;
		}
	}

	private Integer taskFlag;
	private Integer packageFlag;
	private Integer googleFeedFlag;

	// -------------------setter-------------------------
	public void setApiManagerService(ApiManagerService apiManagerService) {
		this.apiManagerService = apiManagerService;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public void setTrackUs(String trackUs) {
		this.trackUs = trackUs;
	}

	public String getTrackUs() {
		return trackUs;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public Integer getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(Integer taskFlag) {
		this.taskFlag = taskFlag;
	}

	public Integer getPackageFlag() {
		return packageFlag;
	}

	public void setPackageFlag(Integer packageFlag) {
		this.packageFlag = packageFlag;
	}

	public void setGoogleFeedFlag(Integer googleFeedFlag) {
		this.googleFeedFlag = googleFeedFlag;
	}

	public Integer getGoogleFeedFlag() {
		return googleFeedFlag;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
}
