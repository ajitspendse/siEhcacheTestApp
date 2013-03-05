package com.terracotta.airport.model;

import java.io.Serializable;


public class Weather implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String weather;
	String wind;
	String temp;
	String visibility;
	Meta meta;
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	@Override
	public String toString() {
		return "Weather [weather=" + weather + ", wind=" + wind + ", temp="
				+ temp + ", visibility=" + visibility + ", meta=" + meta + "]";
	} 
	
}
