/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ChannelTypeDao.java
 * Encoding: UTF-8
 * Date: 2013-5-16
 * History: 
 */
package com.thestore.eam.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thestore.eam.common.Constants;
import com.thestore.eam.dao.IDmpLabelDao;
import com.thestore.eam.domain.BaseEntity;
import com.thestore.eam.domain.DmpLabel;
import com.thestore.eam.domain.DmpLabelValue;

/**
 * DMP标签管理持久层
 * 
 * @author wuwenqi(wuwenqi@yhd.com)
 * @version Revision: 1.00 Date: 2014-9-11
 */
public class DmpLabelDao extends BaseIbatisDao<BaseEntity, Long> implements IDmpLabelDao {

	@Override
	public int saveDmpLabel(DmpLabel dmpLable) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> findLabelByParentId(Long parentId) {
		if (parentId == null) {
			return null;
		}
		DmpLabel conditon = new DmpLabel();
		conditon.setParentId(parentId);
		return this.getSqlSession().selectList("dmpLabel.findLabelByCondition", conditon);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DmpLabel findLabelById(Long id) {
		if (id == null) {
			return null;
		}
		DmpLabel conditon = new DmpLabel();
		conditon.setId(id);
		List<DmpLabel> list = this.getSqlSession().selectList("dmpLabel.findLabelByCondition", conditon);
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> getOneLevelDmpLabel() {
		DmpLabel conditon = new DmpLabel();
		conditon.setParentId(0L);
		return this.getSqlSession().selectList("dmpLabel.findLabelByCondition", conditon);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> getAllDmpLabel() {
		return this.getSqlSession().selectList("dmpLabel.findLabelByCondition");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> getCommonDmpLabels() {
		DmpLabel conditon = new DmpLabel();
		conditon.setIsCommon(Constants.LABEL_IS_COMMON);
		return this.getSqlSession().selectList("dmpLabel.getCommonDmpLabels", conditon);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> findLabelByCondition(DmpLabel condition) {
		return this.getSqlSession().selectList("dmpLabel.findLabelByCondition", condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabelValue> findDmpLabelValue(DmpLabel dmpLabel) {
		return this.queryList("dmpLabel.findDmpLabelValue", dmpLabel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> findDmpLabel(DmpLabel dmpLabel) {
		return this.queryList("dmpLabel.findDmpLabelTotalCount", dmpLabel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabelValue> findDmpLabelValueByLabelIds(List<DmpLabel> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("labels", list);
		return this.getSqlSession().selectList("dmpLabel.findDmpLabelValueByLabelIds", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DmpLabel> findDmpLabelTotal(Long dmpLabelId) {
		DmpLabel conditon = new DmpLabel();
		conditon.setId(dmpLabelId);
		return this.queryList("dmpLabel.findDmpLabelTotalCount", conditon);
	}

	@Override
	public Integer countDmpLabel(DmpLabel dmpLabel) {
		return (Integer) this.getSqlSession().selectOne("dmpLabel.countDmpLabel", dmpLabel);
	}

	@Override
	public int updateDmpLabelRanking(Long dmpLabelId, Integer sort, String updator) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", dmpLabelId);
		params.put("updator", updator);
		params.put("sort", sort);
		return this.getSqlSession().update("dmpLabel.updateDmpLabelRanking", params);
	}

	@Override
	public int getSort(Long parentLabelCategoryId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentLabelCategoryId);
		return this.getSqlSession().update("dmpLabel.getDmpLabelSort", params);
	}

}
