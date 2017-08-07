package com.thestore.eam.service.impl;

import java.util.Date;

import com.thestore.eam.dao.ITrackerDao;
import com.thestore.eam.domain.Tracker;
import com.thestore.eam.service.ITrackerService;

public class TrackerService implements ITrackerService {
	private ITrackerDao trackerDao;

	@Override
	public int saveTracker(Tracker tracker) {
		if (tracker == null) {
			return -1;
		}
		tracker.setTrackTime(new Date());
		return trackerDao.saveTracker(tracker);
	}

	public void setTrackerDao(ITrackerDao trackerDao) {
		this.trackerDao = trackerDao;
	}

}
