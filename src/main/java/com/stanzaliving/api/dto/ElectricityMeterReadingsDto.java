package com.stanzaliving.api.dto;

import java.util.Date;

import com.stanzaliving.api.model.ElectricityMeterDetails;

public class ElectricityMeterReadingsDto {

	private ElectricityMeterDetails electricityMeterDetails;

	private String lastReadingKwah;

	private String lastReadingKwh;

	private Date lastReadingdate;

	private String lastMeterReading;

	private String lastunitBalance;

	public ElectricityMeterDetails getElectricityMeterDetails() {
		return electricityMeterDetails;
	}

	public void setElectricityMeterDetails(ElectricityMeterDetails electricityMeterDetails) {
		this.electricityMeterDetails = electricityMeterDetails;
	}

	public String getLastReadingKwah() {
		return lastReadingKwah;
	}

	public void setLastReadingKwah(String lastReadingKwah) {
		this.lastReadingKwah = lastReadingKwah;
	}

	public String getLastReadingKwh() {
		return lastReadingKwh;
	}

	public void setLastReadingKwh(String lastReadingKwh) {
		this.lastReadingKwh = lastReadingKwh;
	}

	public Date getLastReadingdate() {
		return lastReadingdate;
	}

	public void setLastReadingdate(Date lastReadingdate) {
		this.lastReadingdate = lastReadingdate;
	}

	public String getLastMeterReading() {
		return lastMeterReading;
	}

	public void setLastMeterReading(String lastMeterReading) {
		this.lastMeterReading = lastMeterReading;
	}

	public String getLastunitBalance() {
		return lastunitBalance;
	}

	public void setLastunitBalance(String lastunitBalance) {
		this.lastunitBalance = lastunitBalance;
	}

	@Override
	public String toString() {
		return "ElectricityMeterReadingsDto [electricityMeterDetails=" + electricityMeterDetails + ", lastReadingKwah="
				+ lastReadingKwah + ", lastReadingKwh=" + lastReadingKwh + ", lastReadingdate=" + lastReadingdate
				+ ", lastMeterReading=" + lastMeterReading + ", lastunitBalance=" + lastunitBalance + "]";
	}

	public ElectricityMeterReadingsDto(ElectricityMeterDetails electricityMeterDetails, String lastReadingKwah,
			String lastReadingKwh, Date lastReadingdate, String lastMeterReading, String lastunitBalance) {
		super();
		this.electricityMeterDetails = electricityMeterDetails;
		this.lastReadingKwah = lastReadingKwah;
		this.lastReadingKwh = lastReadingKwh;
		this.lastReadingdate = lastReadingdate;
		this.lastMeterReading = lastMeterReading;
		this.lastunitBalance = lastunitBalance;
	}

	public ElectricityMeterReadingsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
