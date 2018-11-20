package com.stanzaliving.api.dto;

import com.stanzaliving.api.model.ElectricityMeterSubCategory;

public class ElectricityMeterSubCategoryDto {

	private ElectricityMeterSubCategory electricityMeterSubCategory;

	private int count;

	public ElectricityMeterSubCategory getElectricityMeterSubCategory() {
		return electricityMeterSubCategory;
	}

	public void setElectricityMeterSubCategory(ElectricityMeterSubCategory electricityMeterSubCategory) {
		this.electricityMeterSubCategory = electricityMeterSubCategory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ElectricityMeterSubCategoryDto [electricityMeterSubCategory=" + electricityMeterSubCategory + ", count="
				+ count + "]";
	}

	public ElectricityMeterSubCategoryDto(ElectricityMeterSubCategory electricityMeterSubCategory, int count) {
		super();
		this.electricityMeterSubCategory = electricityMeterSubCategory;
		this.count = count;
	}

	public ElectricityMeterSubCategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
