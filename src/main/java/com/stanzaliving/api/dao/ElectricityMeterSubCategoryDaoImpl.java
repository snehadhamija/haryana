package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

@Repository("electricityMeterSubCategoryDao")
public class ElectricityMeterSubCategoryDaoImpl extends AbstractDao<Integer, ElectricityMeterSubCategory>
		implements ElectricityMeterSubCategoryDao {

	public ElectricityMeterSubCategory findById(int id) {
		return getByKey(id);
	}

	public List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategories() {
		Criteria crit = createEntityCriteria();
		return (List<ElectricityMeterSubCategory>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategoriesForElectricityMeterCategory(
			ElectricityMeterCategory electricityMeterCategory) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterCategory", electricityMeterCategory));
		return (List<ElectricityMeterSubCategory>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
