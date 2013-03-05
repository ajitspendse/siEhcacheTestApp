package com.terracotta.airport.model;

import java.io.Serializable;

public class Airport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1154984924235394719L;
	/**
	 * 
	 */

	private String name;
	private String code;
	private String location;
	private String restroomCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Airport [name=" + name + ", code=" + code + ", location="
				+ location + "]";
	}
	
	

}
