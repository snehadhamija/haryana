package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterCategory;

@Repository("electricityMeterCategoryDao")
public class ElectricityMeterCategoryDaoImpl extends AbstractDao<Integer, ElectricityMeterCategory>
		implements ElectricityMeterCategoryDao {

	public ElectricityMeterCategory findById(int id) {
		return getByKey(id);
	}

	public List<ElectricityMeterCategory> findAllElectricityMeterCategories() {
		Criteria crit = createEntityCriteria();
		return (List<ElectricityMeterCategory>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
