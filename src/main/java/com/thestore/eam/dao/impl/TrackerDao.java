/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ChannelTypeDao.java
 * Encoding: UTF-8
 * Date: 2013-5-16
 * History: 
 */
package com.thestore.eam.dao.impl;

import com.thestore.eam.dao.ITrackerDao;
import com.thestore.eam.domain.BaseEntity;
import com.thestore.eam.domain.Tracker;

/**
 * tracker持久层
 * 
 * @author wuwenqi(wuwenqi@yhd.com)
 * @version Revision: 1.00 Date: 2014-9-11
 */
public class TrackerDao extends BaseIbatisDao<BaseEntity, Long> implements ITrackerDao {

	@Override
	public int saveTracker(Tracker tracker) {
		return this.getSqlSession().insert("Tracker.saveTracker", tracker);
	}

}
