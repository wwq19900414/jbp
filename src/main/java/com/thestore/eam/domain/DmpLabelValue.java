package com.thestore.eam.domain;

import java.util.Date;

public class DmpLabelValue extends BaseEntity {

	private static final long serialVersionUID = 7402356612256424330L;
	private Long labelId;
	private Long labelValueId;
	private String labelValueName;
	private String source;
	private String developer;
	private Integer status;
	private Integer userCount;
	private String updateFrequency;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updator;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelValueId(Long labelValueId) {
		this.labelValueId = labelValueId;
	}

	public Long getLabelValueId() {
		return labelValueId;
	}

	public void setLabelValueName(String labelValueName) {
		this.labelValueName = labelValueName;
	}

	public String getLabelValueName() {
		return labelValueName;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setUpdateFrequency(String updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public String getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

}
