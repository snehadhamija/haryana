package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_HOSTEL")
public class LuggageHostel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "HOSTEL_ID", nullable = false)
	private Integer hostelId;

	@Column(name = "IS_ACTIVATED", nullable = false)
	private Boolean isActivated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getHostelId() {
		return hostelId;
	}

	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public String toString() {
		return "LuggageHostel [id=" + id + ", hostelId=" + hostelId + ", isActivated=" + isActivated + "]";
	}

	public LuggageHostel(int id, Integer hostelId, Boolean isActivated) {
		super();
		this.id = id;
		this.hostelId = hostelId;
		this.isActivated = isActivated;
	}

	public LuggageHostel() {
		super();
	}
}
