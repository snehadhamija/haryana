package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_ACTIVITY_STATUS")
public class LuggageActivityStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "STATUS_NAME", nullable = false)
	private String statusName;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_ACTIVITY_ID")
	private LuggageActivity luggageActivity;

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

	public LuggageActivity getLuggageActivity() {
		return luggageActivity;
	}

	public void setLuggageActivity(LuggageActivity luggageActivity) {
		this.luggageActivity = luggageActivity;
	}
}
