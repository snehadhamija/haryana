package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.CartDetail;

public interface CartDetailDao {

	void save(CartDetail cartDetail);

	CartDetail findById(int id);

	CartDetail findCartDetailsForToken(String token);

}
