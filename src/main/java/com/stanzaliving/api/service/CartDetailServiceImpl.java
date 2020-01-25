package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.CartDetailDao;
import com.stanzaliving.api.model.CartDetail;

@Service("cartDetailService")
@Transactional
public class CartDetailServiceImpl implements CartDetailService {

	@Autowired
	private CartDetailDao dao;

	@Override
	public void save(CartDetail cartDetail) {
		dao.save(cartDetail);
	}

	@Override
	public CartDetail findById(int id) {
		return dao.findById(id);
	}

	@Override
	public CartDetail findCartDetailsForToken(String token) {
		return dao.findCartDetailsForToken(token);
	}
}
