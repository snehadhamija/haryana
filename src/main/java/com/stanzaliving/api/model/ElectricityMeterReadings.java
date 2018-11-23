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
@Table(name = "ELECTRICITY_METER_READINGS")
public class ElectricityMeterReadings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "ELECTRICITY_METER_DETAILS_ID")
	private ElectricityMeterDetails electricityMeterDetails;

	@Column(name = "READING_KWAH", nullable = true)
	private String readingKwah;

	@Column(name = "READING_KWH", nullable = true)
	private String readingKwh;

	@Column(name = "METER_READING", nullable = true)
	private String meterReading;

	@Column(name = "UNIT_BALANCE", nullable = true)
	private String unitBalance;

	@Column(name = "READING_DATE", nullable = false)
	private Date readingDate;

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

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public String getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(String meterReading) {
		this.meterReading = meterReading;
	}

	public String getUnitBalance() {
		return unitBalance;
	}

	public void setUnitBalance(String unitBalance) {
		this.unitBalance = unitBalance;
	}
}
