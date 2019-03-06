package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterReadingImages;
import com.stanzaliving.api.model.ElectricityMeterReadings;

@Repository("electricityMeterReadingImagesDao")
public class ElectricityMeterReadingImagesDaoImpl extends AbstractDao<Integer, ElectricityMeterReadingImages>
		implements ElectricityMeterReadingImagesDao {

	@Override
	public void save(ElectricityMeterReadingImages electricityMeterReadingImages) {
		persist(electricityMeterReadingImages);
	}

	@Override
	public void deleteElectricityMeterReadingImages(ElectricityMeterReadingImages electricityMeterReadingImages) {
		delete(electricityMeterReadingImages);
		getSession().flush();
	}

	@Override
	public List<ElectricityMeterReadingImages> findElectricityMeterReadingImagesForElectricityMeterReadings(
			ElectricityMeterReadings electricityMeterReadings) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterReadings", electricityMeterReadings));
		return (List<ElectricityMeterReadingImages>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

}
