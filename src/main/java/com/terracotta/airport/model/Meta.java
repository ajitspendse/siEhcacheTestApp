package com.terracotta.airport.model;

import java.io.Serializable;

public class Meta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String credit;
	String url;
	String updated;
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	@Override
	public String toString() {
		return "Meta [credit=" + credit + ", url=" + url + ", updated="
				+ updated + "]";
	}

}
