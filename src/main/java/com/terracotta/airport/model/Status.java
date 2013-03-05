package com.terracotta.airport.model;

import java.io.Serializable;

public class Status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String avgDelay;
	String closureEnd;
	String closureBegin;
	String type;
	String minDelay;
	String trend;
	String reason;
	String maxDelay;
	String endTime;
	
	public String getAvgDelay() {
		return avgDelay;
	}
	public void setAvgDelay(String avgDelay) {
		this.avgDelay = avgDelay;
	}
	public String getClosureEnd() {
		return closureEnd;
	}
	public void setClosureEnd(String closureEnd) {
		this.closureEnd = closureEnd;
	}
	public String getClosureBegin() {
		return closureBegin;
	}
	public void setClosureBegin(String closureBegin) {
		this.closureBegin = closureBegin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMinDelay() {
		return minDelay;
	}
	public void setMinDelay(String minDelay) {
		this.minDelay = minDelay;
	}
	public String getTrend() {
		return trend;
	}
	public void setTrend(String trend) {
		this.trend = trend;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMaxDelay() {
		return maxDelay;
	}
	public void setMaxDelay(String maxDelay) {
		this.maxDelay = maxDelay;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Status [avgDelay=" + avgDelay + ", closureEnd=" + closureEnd
				+ ", closureBegin=" + closureBegin + ", type=" + type
				+ ", minDelay=" + minDelay + ", trend=" + trend + ", reason="
				+ reason + ", maxDelay=" + maxDelay + ", endTime=" + endTime
				+ "]";
	}


}
