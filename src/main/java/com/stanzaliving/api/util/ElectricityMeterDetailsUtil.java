package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.ElectricityMeterReadingsDto;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;

@Component
public class ElectricityMeterDetailsUtil {

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	public List<ElectricityMeterReadingsDto> createElectricityMeterReadingsDtoList(
			List<ElectricityMeterDetails> electricityMeterDetails) {
		List<ElectricityMeterReadingsDto> electricityMeterReadingsDtos = new ArrayList<>();
		for (ElectricityMeterDetails ed : electricityMeterDetails) {
			ElectricityMeterReadingsDto electricityMeterReadingsDto = createElectricityMeterReadingsDto(ed);
			electricityMeterReadingsDtos.add(electricityMeterReadingsDto);
		}
		return electricityMeterReadingsDtos;
	}

	public ElectricityMeterReadingsDto createElectricityMeterReadingsDto(
			ElectricityMeterDetails electricityMeterDetails) {
		ElectricityMeterReadingsDto electricityMeterReadingsDto = new ElectricityMeterReadingsDto();
		electricityMeterReadingsDto.setElectricityMeterDetails(electricityMeterDetails);
		ElectricityMeterReadings electricityMeterReadings = electricityMeterReadingsService
				.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
		if (electricityMeterReadings != null) {
			electricityMeterReadingsDto = setExistingElectricityMeterReadings(electricityMeterReadingsDto,
					electricityMeterReadings);
		}
		return electricityMeterReadingsDto;
	}

	public ElectricityMeterReadingsDto setExistingElectricityMeterReadings(
			ElectricityMeterReadingsDto electricityMeterReadingsDto,
			ElectricityMeterReadings electricityMeterReadings) {
		electricityMeterReadingsDto.setLastReadingdate(electricityMeterReadings.getReadingDate());
		electricityMeterReadingsDto.setLastReadingKwah(electricityMeterReadings.getReadingKwah());
		electricityMeterReadingsDto.setLastMeterReading(electricityMeterReadings.getMeterReading());
		electricityMeterReadingsDto.setLastunitBalance(electricityMeterReadings.getUnitBalance());
		electricityMeterReadingsDto.setLastReadingKwh(electricityMeterReadings.getReadingKwh());
		return electricityMeterReadingsDto;
	}
}
