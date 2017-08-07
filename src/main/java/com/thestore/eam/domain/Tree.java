package com.thestore.eam.domain;

import java.util.Date;
import java.util.List;

/**
 * 树型实体类
 * 
 * @author wuwenqi
 * 
 */
public class Tree extends BaseEntity {
	private static final long serialVersionUID = 1812725006410912936L;
	private String name;
	private Long parentId;
	private Integer level;

	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updator;

	private List<Tree> subList;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setSubList(List<Tree> subList) {
		this.subList = subList;
	}

	public List<Tree> getSubList() {
		return subList;
	}

}
