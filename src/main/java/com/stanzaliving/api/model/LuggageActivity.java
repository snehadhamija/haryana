package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_ACTIVITY")
public class LuggageActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ACTIVITY_NAME", nullable = false)
	private String activityName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "LuggageActivity [id=" + id + ", activityName=" + activityName + ", isActive=" + isActive + "]";
	}

	public LuggageActivity(int id, String activityName, Boolean isActive) {
		super();
		this.id = id;
		this.activityName = activityName;
		this.isActive = isActive;
	}

	public LuggageActivity() {
		super();
	}
}
