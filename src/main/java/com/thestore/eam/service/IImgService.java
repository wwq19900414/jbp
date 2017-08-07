package com.thestore.eam.service;

import com.thestore.eam.domain.ImgInfo;

public interface IImgService {
	public ImgInfo saveImg(ImgInfo img);

	public ImgInfo findNextImg(ImgInfo img);

	public ImgInfo findPreImg(ImgInfo img);
}
