package com.thestore.eam.web.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.thestore.eam.domain.ImgInfo;
import com.thestore.eam.service.IImgService;
import com.thestore.eam.utils.HtmlUtils;

/**
 * DMP标签管理控制器
 * 
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2015-07-21
 */
@Namespace("/")
@Results({ @Result(name = "img", location = "/imgCollect/imgMain.jsp"),
		@Result(name = "showImg", location = "/imgCollect/imgView.jsp"),
		@Result(name = "showPageImg", location = "/imgCollect/imgPage.jsp") })
public class ImgAction extends BaseAction {
	private static final long serialVersionUID = 5838662157923910080L;
	private ImgInfo img = new ImgInfo();
	private List<ImgInfo> imgList;
	private IImgService imgService;

	@Action(value = "img")
	public String img() {
		return "img";
	}

	@Action(value = "showImg")
	public String showImg() {
		img = imgService.saveImg(img);
		return "showImg";
	}

	@Action(value = "showPageImg")
	public String showPageImg() {
		List<String> imgUrlList = HtmlUtils.getImgsFromHtml(img.getImgUrl());
		if (imgList == null) {
			imgList = new ArrayList<ImgInfo>();
		}
		for (String imgUrl : imgUrlList) {
			ImgInfo img = new ImgInfo();
			img.setImgUrl(imgUrl);
			imgList.add(img);
		}
		return "showPageImg";
	}

	@Action(value = "showNextImg")
	public void showNextImg() {
		ImgInfo nextImg = imgService.findNextImg(img);
		JSONObject jsonObject = JSONObject.fromObject(nextImg);
		this.writeHTML(jsonObject.toString());
	}

	@Action(value = "showPreImg")
	public void showPreImg() {
		ImgInfo nextImg = imgService.findPreImg(img);
		JSONObject jsonObject = JSONObject.fromObject(nextImg);
		this.writeHTML(jsonObject.toString());
	}

	/******************* getter and setter *******************/
	public void setImgService(IImgService imgService) {
		this.imgService = imgService;
	}

	public void setImg(ImgInfo img) {
		this.img = img;
	}

	public ImgInfo getImg() {
		return img;
	}

	public void setImgList(List<ImgInfo> imgList) {
		this.imgList = imgList;
	}

	public List<ImgInfo> getImgList() {
		return imgList;
	}

}
