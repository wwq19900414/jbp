/**
 * Copyright (C), 2011-2016 The Store
 * File Name: BaseEntity.java
 * Encoding: UTF-8
 * Date: Sep 8, 2011
 * History: 
 */
package com.thestore.eam.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 8, 2011
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1135912663072937068L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		BaseEntity other = (BaseEntity) obj;
		if (!id.equals(other.id)) {
			return false;
		}

		return true;
	}

}
