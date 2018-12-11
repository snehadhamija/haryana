package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ELECTRICITY_METER_READINGS_UNIT")
public class ElectricityMeterReadingsUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "READING_NAME", nullable = false)
	private String readingName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReadingName() {
		return readingName;
	}

	public void setReadingName(String readingName) {
		this.readingName = readingName;
	}
}
