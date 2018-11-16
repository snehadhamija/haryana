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
@Table(name = "ELECTRICITY_METER_DETAILS")
public class ElectricityMeterDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "METER_NAME", nullable = true)
	private String materName;

	@Column(name = "METER_NUMBER", nullable = false)
	private String materNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ELECTRICITY_METER_SUB_CATEGORY_ID")
	private ElectricityMeterSubCategory electricityMeterSubCategory;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "FLOOR_ID", nullable = true)
	private Floor floor;

}
