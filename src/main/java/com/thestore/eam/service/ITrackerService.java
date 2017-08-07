package com.thestore.eam.service;

import com.thestore.eam.domain.Tracker;

public interface ITrackerService {
	/**
	 * 保存一条tracker日志
	 * 
	 * @param dmpLable
	 * @return
	 */
	int saveTracker(Tracker tracker);

}
