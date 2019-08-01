package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageHostel;

public interface LuggageHostelService {

	void save(LuggageHostel luggageHostel);

	LuggageHostel findById(int id);

	List<LuggageHostel> findAllLuggageHostels();

	boolean findIfLuggageModuleActivatedForCurrentHostel(Integer hostelId);

	boolean isLuggageActivatedForHostel(int hostelId);
}
