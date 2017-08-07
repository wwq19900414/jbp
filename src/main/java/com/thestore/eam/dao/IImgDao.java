package com.thestore.eam.dao;

import java.util.List;

import com.thestore.eam.domain.ImgInfo;

public interface IImgDao {

	/**
	 * 保存一个图片信息
	 * 
	 * @param img
	 * @return
	 */
	int saveImg(ImgInfo img);

	/**
	 * 获取指定url的图片信息
	 * 
	 * @param imgUrl
	 * @return
	 */
	List<ImgInfo> findImg(String imgUrl);

	ImgInfo findNextImg(ImgInfo img);

	ImgInfo findPreImg(ImgInfo img);

}
