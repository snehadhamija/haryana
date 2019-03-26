package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_STORAGE_ROOM")
public class LuggageStoreRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ROOM_NAME", nullable = false)
	private String roomName;

	@Column(name = "HOSTEL_ID", nullable = false)
	private Integer hostelId;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getHostelId() {
		return hostelId;
	}

	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
	}
}
