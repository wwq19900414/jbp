package com.thestore.eam.service.impl;

import java.util.List;

import com.thestore.eam.dao.IImgDao;
import com.thestore.eam.domain.ImgInfo;
import com.thestore.eam.service.IImgService;
import com.thestore.eam.utils.FileUtils;

public class ImgService implements IImgService {
	private IImgDao imgDao;

	@Override
	public ImgInfo saveImg(ImgInfo img) {
		String imgUrl = img.getImgUrl();
		List<ImgInfo> imgList = imgDao.findImg(imgUrl);
		if (imgList == null || imgList.isEmpty()) {
			String savePath = "D:/pic";
			String fileName = img.getHost() + "_" + System.currentTimeMillis() + "." + img.getImgType();
			FileUtils.download(imgUrl, fileName, savePath);
			imgDao.saveImg(img);
			return img;
		} else {
			System.out.println("already exists this pic");
			return imgList.get(0);
		}

	}

	@Override
	public ImgInfo findNextImg(ImgInfo img) {
		return imgDao.findNextImg(img);
	}

	@Override
	public ImgInfo findPreImg(ImgInfo img) {
		return imgDao.findPreImg(img);
	}

	public void setImgDao(IImgDao imgDao) {
		this.imgDao = imgDao;
	}

}
