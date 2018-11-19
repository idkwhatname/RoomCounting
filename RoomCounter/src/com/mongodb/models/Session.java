package com.mongodb.models;

public class Session {
	
	private String sessionID;
	private String sessionName;
	private String sessionNumber;
	private String speaker;
	private String timeSlot;
	private String room;
	private String startCount;
	private String middleCount;
	private String endCount;
	
	
	public void setSessionID(String id) {
		sessionID = id;
	}
	
	public String getSessionID() {
		return sessionID;
	}
	
	public void setSessionName(String name) {
		sessionName = name;
	}
	public void setSessionNumber(String number) {
		sessionNumber = number;
	}
	public void setSpeaker(String name) {
		speaker = name;
	}
	public void setTimeSlot(String time) {
		timeSlot = time;
	}
	
	public void setRoom(String r) {
		room = r;
	}
	
	public String getSessionName() {
		return sessionName;
	}
	
	public String getSessionNumber() {
		return sessionNumber;
	}
	
	public String getSpeaker() {
		return speaker;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public String getRoom() {
		return room;
	}

	public String getEndCount() {
		return endCount;
	}

	public void setEndCount(String endCount) {
		this.endCount = endCount;
	}

	public String getStartCount() {
		return startCount;
	}

	public void setStartCount(String startCount) {
		this.startCount = startCount;
	}

	public String getMiddleCount() {
		return middleCount;
	}

	public void setMiddleCount(String middleCount) {
		this.middleCount = middleCount;
	}

}
