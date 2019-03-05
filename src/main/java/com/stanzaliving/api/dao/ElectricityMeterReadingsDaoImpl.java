package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

@Repository("electricityMeterReadingsDao")
public class ElectricityMeterReadingsDaoImpl extends AbstractDao<Integer, ElectricityMeterReadings>
		implements ElectricityMeterReadingsDao {

	@Override
	public void save(ElectricityMeterReadings electricityMeterReadings) {
		persist(electricityMeterReadings);
	}

	@Override
	public void saveOrUpdateElectricityMeterReadings(ElectricityMeterReadings electricityMeterReadings) {
		saveOrUpdate(electricityMeterReadings);
	}

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

	@Override
	public List<ElectricityMeterReadings> findAskedNumberElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails, String numberOfReadings) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterDetails", electricityMeterDetails));
		crit.addOrder(Order.desc("id"));
		crit.setMaxResults(Integer.valueOf(numberOfReadings));
		if (!crit.list().isEmpty()) {
			return (List<ElectricityMeterReadings>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
		}
		return null;
	}

	@Override
	public ElectricityMeterReadings findLastElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterDetails", electricityMeterDetails));
		crit.addOrder(Order.desc("id"));
		crit.setMaxResults(1);
		if (crit.uniqueResult() != null) {
			return (ElectricityMeterReadings) crit.uniqueResult();
		}
		return null;
	}

}
