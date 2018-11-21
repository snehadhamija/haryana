package com.stanzaliving.api.dto;

import java.util.Date;

import com.stanzaliving.api.model.ElectricityMeterDetails;

public class ElectricityMeterReadingsDto {

	private ElectricityMeterDetails electricityMeterDetails;

	private String lastReadingKwah;

	private String lastReadingKwh;

	private Date lastReadingdate;

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

	@Override
	public String toString() {
		return "ElectricityMeterReadingsDto [electricityMeterDetails=" + electricityMeterDetails + ", lastReadingKwah="
				+ lastReadingKwah + ", lastReadingKwh=" + lastReadingKwh + ", lastReadingdate=" + lastReadingdate + "]";
	}

	public ElectricityMeterReadingsDto(ElectricityMeterDetails electricityMeterDetails, String lastReadingKwah,
			String lastReadingKwh, Date lastReadingdate) {
		super();
		this.electricityMeterDetails = electricityMeterDetails;
		this.lastReadingKwah = lastReadingKwah;
		this.lastReadingKwh = lastReadingKwh;
		this.lastReadingdate = lastReadingdate;
	}

	public ElectricityMeterReadingsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
