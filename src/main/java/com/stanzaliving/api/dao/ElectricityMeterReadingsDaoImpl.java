package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

@Repository("electricityMeterReadingsDao")
public class ElectricityMeterReadingsDaoImpl extends AbstractDao<Integer, ElectricityMeterReadings>
		implements ElectricityMeterReadingsDao {

	@Override
	public ElectricityMeterReadings findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<ElectricityMeterReadings> findAllElectricityMeterReadings() {
		Criteria crit = createEntityCriteria();
		return (List<ElectricityMeterReadings>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<ElectricityMeterReadings> findAllElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterDetails", electricityMeterDetails));
		return (List<ElectricityMeterReadings>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

}
