package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ELECTRICITY_METER_READING_IMAGES")
public class ElectricityMeterReadingImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ELECTRICITY_METER_READINGS_ID")
	private ElectricityMeterReadings electricityMeterReadings;

	@Column(name = "IMG_URL", nullable = false)
	private String imgUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ElectricityMeterReadings getElectricityMeterReadings() {
		return electricityMeterReadings;
	}

	public void setElectricityMeterReadings(ElectricityMeterReadings electricityMeterReadings) {
		this.electricityMeterReadings = electricityMeterReadings;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
