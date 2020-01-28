package com.vcare.api.service;

import com.vcare.api.model.CartDetail;

public interface CartDetailService {

	void save(CartDetail cartDetail);

	CartDetail findById(int id);

	CartDetail findCartDetailsForToken(String token);

}
