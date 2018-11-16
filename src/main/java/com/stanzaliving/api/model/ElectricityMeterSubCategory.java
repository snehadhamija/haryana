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
@Table(name = "ELECTRICITY_METER_SUB_CATEGORY")
public class ElectricityMeterSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "SUB_CATEGORY_NAME", nullable = false)
	private String subCategoryName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ELECTRICITY_METER_CATEGORY_ID")
	private ElectricityMeterCategory electricityMeterCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public ElectricityMeterCategory getElectricityMeterCategory() {
		return electricityMeterCategory;
	}

	public void setElectricityMeterCategory(ElectricityMeterCategory electricityMeterCategory) {
		this.electricityMeterCategory = electricityMeterCategory;
	}

}
