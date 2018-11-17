package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.model.Hostel;

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
			ElectricityMeterSubCategory electricityMeterSubCategory, Hostel hostel) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("electricityMeterSubCategory", electricityMeterSubCategory));
		crit.add(Restrictions.eq("hostel", hostel));
		return (List<ElectricityMeterDetails>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

}
