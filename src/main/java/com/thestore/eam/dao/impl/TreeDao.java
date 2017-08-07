/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ChannelTypeDao.java
 * Encoding: UTF-8
 * Date: 2013-5-16
 * History: 
 */
package com.thestore.eam.dao.impl;

import java.util.List;

import com.thestore.eam.dao.ITreeDao;
import com.thestore.eam.domain.BaseEntity;
import com.thestore.eam.domain.Tree;

/**
 * 数型数据持久层
 * 
 * @author wuwenqi(wuwenqi@yhd.com)
 * @version Revision: 1.00 Date: 2017-03-18
 */
public class TreeDao extends BaseIbatisDao<BaseEntity, Long> implements ITreeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tree> findAllTree() {
		return this.getSqlSession().selectList("findTree");
	}

}
