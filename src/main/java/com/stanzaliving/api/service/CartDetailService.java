package com.stanzaliving.api.service;

import com.stanzaliving.api.model.CartDetail;

public interface CartDetailService {

	void save(CartDetail cartDetail);

	CartDetail findById(int id);

	CartDetail findCartDetailsForToken(String token);

}
