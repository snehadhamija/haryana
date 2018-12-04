package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.ElectricityMeterDetailsDao;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

@Service("electricityMeterDetailsService")
@Transactional
public class ElectricityMeterDetailsServiceImpl implements ElectricityMeterDetailsService {

	@Autowired
	private ElectricityMeterDetailsDao dao;

	public ElectricityMeterDetails findById(int id) {
		return dao.findById(id);
	}

	public List<ElectricityMeterDetails> findAllElectricityMeterDetails() {
		return dao.findAllElectricityMeterDetails();
	}

	@Override
	public List<ElectricityMeterDetails> findAllElectricityMeterDetailsForSubCategoryInHostel(
			ElectricityMeterSubCategory electricityMeterSubCategory, int hostelId) {
		return dao.findAllElectricityMeterDetailsForSubCategoryInHostel(electricityMeterSubCategory, hostelId);
	}

}
