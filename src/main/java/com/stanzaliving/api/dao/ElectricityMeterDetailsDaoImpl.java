package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

@Repository("electricityMeterDetailsDao")
public class ElectricityMeterDetailsDaoImpl extends AbstractDao<Integer, ElectricityMeterDetails>
		implements ElectricityMeterDetailsDao {

	public ElectricityMeterDetails findById(int id) {
		return getByKey(id);
	}

	public List<ElectricityMeterDetails> findAllElectricityMeterDetails() {
		Criteria crit = createEntityCriteria();
		return (List<ElectricityMeterDetails>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<ElectricityMeterDetails> findAllElectricityMeterDetailsForSubCategoryInHostel(
			ElectricityMeterSubCategory electricityMeterSubCategory, int hostelId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterSubCategory", electricityMeterSubCategory));
		crit.add(Restrictions.eq("hostelId", hostelId));
		crit.add(Restrictions.eq("isActive", true));
		return (List<ElectricityMeterDetails>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<ElectricityMeterDetails> findAllElectricityMeterDetailsInHostel(int hostelId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("hostelId", hostelId));
		crit.add(Restrictions.eq("isActive", true));
		return (List<ElectricityMeterDetails>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

}
