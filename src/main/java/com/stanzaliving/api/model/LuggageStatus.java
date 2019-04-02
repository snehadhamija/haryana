package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_STATUS")
public class LuggageStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "STATUS_NAME", nullable = false)
	private String statusName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "LuggageStatus [id=" + id + ", statusName=" + statusName + "]";
	}

	public LuggageStatus(int id, String statusName) {
		super();
		this.id = id;
		this.statusName = statusName;
	}

	public LuggageStatus() {
		super();
	}
}
