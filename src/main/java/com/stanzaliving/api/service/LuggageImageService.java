package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageImage;

public interface LuggageImageService {

	void save(LuggageImage luggageImage);

	LuggageImage findById(int id);
}
