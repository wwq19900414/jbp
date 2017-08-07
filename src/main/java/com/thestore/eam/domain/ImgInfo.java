package com.thestore.eam.domain;

import java.util.Date;

import com.thestore.eam.utils.DateUtils;

public class ImgInfo extends BaseEntity {

	private static final long serialVersionUID = -6992994116397790258L;
	private String host;
	private String imgUrl;
	private String imgType;
	private Date createTime;
	private String createTimeStr;

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		if (imgUrl != null && !imgUrl.isEmpty()) {
			if (imgUrl.contains("://")) {
				int start = imgUrl.indexOf("://");
				String tempUrl = imgUrl.substring(start + 3);

				int begin = tempUrl.indexOf("/");
				return tempUrl.substring(0, begin);
			} else {
				int begin = imgUrl.indexOf("/");
				return imgUrl.substring(0, begin);
			}
		} else {
			return host;
		}
	}

	public void setImgUrl(String imageUrl) {
		this.imgUrl = imageUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getImgType() {
		if (imgUrl != null && !imgUrl.isEmpty()) {
			int loc = imgUrl.lastIndexOf(".");
			return imgUrl.substring(loc + 1);
		} else {
			return imgType;
		}
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCreateTimeStr() {
		if (createTime != null) {
			return DateUtils.dateToStr(createTime, "yyyy-MM-dd HH:mm:ss");
		}
		return createTimeStr;
	}

}
