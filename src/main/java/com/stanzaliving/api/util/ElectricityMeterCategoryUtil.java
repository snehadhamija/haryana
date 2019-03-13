package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterDetails;

@Component
public class ElectricityMeterCategoryUtil {

	public List<ElectricityMeterCategory> createElectricityMeterCategoryList(
			List<ElectricityMeterDetails> electricityMeterDetails) {
		List<ElectricityMeterCategory> electricityMeterCategories = new ArrayList<>();
		electricityMeterDetails.stream().forEach(meter -> {
			ElectricityMeterCategory electricityMeterCategory = meter.getElectricityMeterSubCategory()
					.getElectricityMeterCategory();
			if (!electricityMeterCategories.contains(electricityMeterCategory)) {
				electricityMeterCategories.add(electricityMeterCategory);
			}
		});
		return electricityMeterCategories;
	}
}
