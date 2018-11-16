package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.Hostel;

public interface HostelService {

	Hostel findById(int id);

	List<Hostel> findAllHostels();
}
