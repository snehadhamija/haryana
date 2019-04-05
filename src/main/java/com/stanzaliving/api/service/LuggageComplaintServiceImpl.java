package com.stanzaliving.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Service("luggageComplaintService")
@Transactional
public class LuggageComplaintServiceImpl implements LuggageComplaintService {

	@Override
	public HashMap<String, Object> createComplaintObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails) {
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> subMap = new HashMap<>();
		subMap.put("id", 13);
		map.put("complainType", subMap);
		subMap.put("id", 54);
		map.put("complainTypeCategory", subMap);
		List<Object> list = new ArrayList<>();
		map.put("complaintTypeSubCategories", list);
		map.put("description", "Testing Complaint flow for luggage!");
		map.put("status", "0");
		map.put("complaintImages", list);
		return map;
	}
}
