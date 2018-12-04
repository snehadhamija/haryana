//package com.stanzaliving.api.dao;
//
//import java.util.List;
//
//import org.hibernate.Criteria;
//import org.springframework.stereotype.Repository;
//
//import com.stanzaliving.api.model.Hostel;
//
//@Repository("hostelDao")
//public class HostelDaoImpl extends AbstractDao<Integer, Hostel> implements HostelDao {
//
//	public Hostel findById(int id) {
//		return getByKey(id);
//	}
//
//	public List<Hostel> findAllHostels() {
//		Criteria crit = createEntityCriteria();
//		return (List<Hostel>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
//	}
//}
