package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageImageService {

	void save(LuggageImage luggageImage);

	LuggageImage findById(int id);

	List<LuggageImage> findAllLuggageImages();

	List<Object> findLuggageImageForLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail);
}
