package com.thestore.eam.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DmpLabel extends BaseEntity {

	private static final long serialVersionUID = 1783546600437723001L;
	private String labelId;// 标签ID,唯一标识标签的
	private String labelName;// 标签名称，当时标签类目时是类目名称
	private Long parentId;// 所属标签类目id,或是父标签类目ID
	private String labelDesc;// 标签描述
	private Integer isLabel;// 是否是标签，0-是标签类目，1-是标签
	private Integer isCommon;// 是否是常用标签，0-非常用，1-常用
	private Integer isSingleSelect;// 是否相同开发者互斥
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updator;

	private String labelCategoryDesc;// 标签所属类目信息（用于标签详情页显示）
	private Integer valueCount;// 含有的标签值的数量
	private Integer userCount;// 覆盖的人群数量
	private String ids;// 用于存放以逗号分隔的多个id
	private Integer sort;// 排名字段

	private List<DmpLabelValue> labelValues = new ArrayList<DmpLabelValue>();// 标签值

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getLabelDesc() {
		return labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}

	public Integer getIsLabel() {
		return isLabel;
	}

	public void setIsLabel(Integer isLabel) {
		this.isLabel = isLabel;
	}

	public Integer getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}

	public Integer getIsSingleSelect() {
		return isSingleSelect;
	}

	public void setIsSingleSelect(Integer isSingleSelect) {
		this.isSingleSelect = isSingleSelect;
	}

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

	public void setLabelCategoryDesc(String labelCategoryDesc) {
		this.labelCategoryDesc = labelCategoryDesc;
	}

	public String getLabelCategoryDesc() {
		return labelCategoryDesc;
	}

	public void setValueCount(Integer valueCount) {
		this.valueCount = valueCount;
	}

	public Integer getValueCount() {
		return valueCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public List<DmpLabelValue> getLabelValues() {
		return labelValues;
	}

	public void setLabelValues(List<DmpLabelValue> labelValues) {
		this.labelValues = labelValues;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void addLabelValue(DmpLabelValue value) {
		this.labelValues.add(value);
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
	}
}
