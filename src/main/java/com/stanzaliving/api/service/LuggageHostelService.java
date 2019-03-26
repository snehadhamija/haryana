package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageHostel;

public interface LuggageHostelService {

	void save(LuggageHostel luggageHostel);

	LuggageHostel findById(int id);
}
