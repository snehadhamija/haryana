package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.Hostel;

public interface HostelDao {

	Hostel findById(int id);

	List<Hostel> findAllHostels();
}
