package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.HostelDao;
import com.stanzaliving.api.model.Hostel;

@Service("hostelService")
@Transactional
public class HostelServiceImpl implements HostelService{

	@Autowired
	private HostelDao dao;

	public Hostel findById(int id) {
		return dao.findById(id);
	}

	public List<Hostel> findAllHostels() {
		return dao.findAllHostels();
	}

}
