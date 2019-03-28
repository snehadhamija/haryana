package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageImageDao {

	void save(LuggageImage luggageImage);

	LuggageImage findById(int id);

	List<LuggageImage> findAllLuggageImages();

	List<Object> findLuggageImageForLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail);
}
