package com.terracotta.airport.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class AirportStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String ICAO;
	String state;
	Status status;
	String delay;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("ICAO")
	public String getICAO() {
		return ICAO;
	}
	@JsonProperty("ICAO")
	public void setICAO(String iCAO) {
		ICAO = iCAO;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	@JsonProperty("IATA")
	public String getIATA() {
		return IATA;
	}
	@JsonProperty("IATA")
	public void setIATA(String iATA) {
		IATA = iATA;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	String IATA;
	String city;
	Weather weather;
	@Override
	public String toString() {
		return "AirportStatus [name=" + name + ", ICAO=" + ICAO + ", state="
				+ state + ", status=" + status + ", delay=" + delay + ", IATA="
				+ IATA + ", city=" + city + ", weather=" + weather + "]";
	}
	

}
