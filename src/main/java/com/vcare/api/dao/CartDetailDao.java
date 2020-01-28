package com.vcare.api.dao;

import com.vcare.api.model.CartDetail;

public interface CartDetailDao {

	void save(CartDetail cartDetail);

	CartDetail findById(int id);

	CartDetail findCartDetailsForToken(String token);

}
