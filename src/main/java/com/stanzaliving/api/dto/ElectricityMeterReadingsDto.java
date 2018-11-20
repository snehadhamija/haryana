package com.stanzaliving.api.dto;

import com.stanzaliving.api.model.ElectricityMeterDetails;

public class ElectricityMeterReadingsDto {

	private ElectricityMeterDetails electricityMeterDetails;

	private String lastReadingKwah;

	private String lastReadingKwh;

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

	@Override
	public String toString() {
		return "ElectricityMeterReadingsDto [electricityMeterDetails=" + electricityMeterDetails + ", lastReadingKwah="
				+ lastReadingKwah + ", lastReadingKwh=" + lastReadingKwh + "]";
	}

	public ElectricityMeterReadingsDto(ElectricityMeterDetails electricityMeterDetails, String lastReadingKwah,
			String lastReadingKwh) {
		super();
		this.electricityMeterDetails = electricityMeterDetails;
		this.lastReadingKwah = lastReadingKwah;
		this.lastReadingKwh = lastReadingKwh;
	}

	public ElectricityMeterReadingsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
