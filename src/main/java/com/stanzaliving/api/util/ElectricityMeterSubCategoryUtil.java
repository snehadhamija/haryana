package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.ElectricityMeterSubCategoryDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class ElectricityMeterSubCategoryUtil {

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	public List<ElectricityMeterSubCategoryDto> createElectricityMeterSubCategoryDtoList(
			ElectricityMeterCategory electricityMeterCategory, HttpServletRequest request) {
		List<ElectricityMeterSubCategory> electricityMeterSubCategories = electricityMeterSubCategoryService
				.findAllElectricityMeterSubCategoriesForElectricityMeterCategory(electricityMeterCategory);
		List<ElectricityMeterSubCategoryDto> electricityMeterSubCategoryDtos = new ArrayList<>();
		if (!electricityMeterSubCategories.isEmpty()) {
			UserDto userDto = springRestClientService.getUserDto(request);
			for (ElectricityMeterSubCategory electricityMeterSubCategory : electricityMeterSubCategories) {
				ElectricityMeterSubCategoryDto electricityMeterSubCategoryDto = createElectricityMeterSubCategoryDto(
						electricityMeterSubCategory, userDto);
				electricityMeterSubCategoryDtos.add(electricityMeterSubCategoryDto);
			}
		}
		return electricityMeterSubCategoryDtos;
	}

	public ElectricityMeterSubCategoryDto createElectricityMeterSubCategoryDto(
			ElectricityMeterSubCategory electricityMeterSubCategory, UserDto userDto) {
		List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsService
				.findAllElectricityMeterDetailsForSubCategoryInHostel(electricityMeterSubCategory,
						userDto.getHostelID());
		ElectricityMeterSubCategoryDto electricityMeterSubCategoryDto = new ElectricityMeterSubCategoryDto();
		electricityMeterSubCategoryDto.setElectricityMeterSubCategory(electricityMeterSubCategory);
		electricityMeterSubCategoryDto.setCount(electricityMeterDetails.size());
		return electricityMeterSubCategoryDto;
	}
}
