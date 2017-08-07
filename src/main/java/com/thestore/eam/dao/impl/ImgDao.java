/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ChannelTypeDao.java
 * Encoding: UTF-8
 * Date: 2013-5-16
 * History: 
 */
package com.thestore.eam.dao.impl;

import java.util.List;

import com.thestore.eam.dao.IImgDao;
import com.thestore.eam.domain.BaseEntity;
import com.thestore.eam.domain.ImgInfo;

/**
 * DMP标签管理持久层
 * 
 * @author wuwenqi(wuwenqi@yhd.com)
 * @version Revision: 1.00 Date: 2014-9-11
 */
public class ImgDao extends BaseIbatisDao<BaseEntity, Long> implements IImgDao {

	@Override
	public int saveImg(ImgInfo img) {
		return this.getSqlSession().insert("Img.saveImg", img);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImgInfo> findImg(String imgUrl) {
		ImgInfo con = new ImgInfo();
		con.setImgUrl(imgUrl);
		return this.getSqlSession().selectList("Img.findImg", con);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImgInfo findNextImg(ImgInfo img) {
		List<ImgInfo> imgList = this.getSqlSession().selectList("Img.findNextImg", img);
		if (imgList != null && !imgList.isEmpty()) {
			return imgList.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImgInfo findPreImg(ImgInfo img) {
		List<ImgInfo> imgList = this.getSqlSession().selectList("Img.findPreImg", img);
		if (imgList != null && !imgList.isEmpty()) {
			return imgList.get(0);
		} else {
			return null;
		}
	}
}
