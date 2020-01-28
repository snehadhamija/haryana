package com.vcare.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.amazonaws.util.CollectionUtils;
import com.amazonaws.util.StringUtils;
import com.vcare.api.model.CartDetail;

@Repository("cartDetailDao")
public class CartDetailDaoImpl extends AbstractDao<Integer, CartDetail> implements CartDetailDao {

	@Override
	public void save(CartDetail cartDetail) {
		persist(cartDetail);
	}

	@Override
	public CartDetail findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CartDetail findCartDetailsForToken(String token) {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("token", token));
		if (!CollectionUtils.isNullOrEmpty(crit.list())) {
			return (CartDetail) crit.list().get(0);
		}
		return null;
	}
}
