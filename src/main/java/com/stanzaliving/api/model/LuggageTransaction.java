package com.stanzaliving.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_TRANSACTION")
public class LuggageTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_ACTIVITY_ID")
	private LuggageActivity luggageActivity;

	@Column(name = "NUMBER_OF_BAGS", nullable = false)
	private Integer numberOfBags;

	@Column(name = "TOTAL_WEIGHT", nullable = false)
	private String totalWeight;

	@Column(name = "EXPECTED_DATE", nullable = false)
	private Date expectedDate;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_STORAGE_ROOM_ID")
	private LuggageStoreRoom luggageStoreRoom;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LuggageActivity getLuggageActivity() {
		return luggageActivity;
	}

	public void setLuggageActivity(LuggageActivity luggageActivity) {
		this.luggageActivity = luggageActivity;
	}

	public Integer getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(Integer numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public LuggageStoreRoom getLuggageStoreRoom() {
		return luggageStoreRoom;
	}

	public void setLuggageStoreRoom(LuggageStoreRoom luggageStoreRoom) {
		this.luggageStoreRoom = luggageStoreRoom;
	}
}
