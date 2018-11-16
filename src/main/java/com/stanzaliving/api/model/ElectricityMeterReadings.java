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
@Table(name = "ELECTRICITY_METER_READINGS")
public class ElectricityMeterReadings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "ELECTRICITY_METER_DETAILS_ID")
	private ElectricityMeterDetails electricityMeterDetails;

	@Column(name = "READING_KWAH", nullable = false)
	private String readingKwah;

	@Column(name = "READING_KWH", nullable = false)
	private String readingKwh;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ElectricityMeterDetails getElectricityMeterDetails() {
		return electricityMeterDetails;
	}

	public void setElectricityMeterDetails(ElectricityMeterDetails electricityMeterDetails) {
		this.electricityMeterDetails = electricityMeterDetails;
	}

	public String getReadingKwah() {
		return readingKwah;
	}

	public void setReadingKwah(String readingKwah) {
		this.readingKwah = readingKwah;
	}

	public String getReadingKwh() {
		return readingKwh;
	}

	public void setReadingKwh(String readingKwh) {
		this.readingKwh = readingKwh;
	}
}
