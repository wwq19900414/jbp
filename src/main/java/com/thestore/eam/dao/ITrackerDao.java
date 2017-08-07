package com.thestore.eam.dao;

import com.thestore.eam.domain.Tracker;

public interface ITrackerDao {

	/**
	 * 保存一条tracker日志
	 * 
	 * @param tracker
	 * @return
	 */
	int saveTracker(Tracker tracker);

}
