package com.stanzaliving.api.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionStatusDao;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageActivityStatus;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionStatus;

@Service("luggageTransactionStatusService")
@Transactional
public class LuggageTransactionStatusServiceImpl implements LuggageTransactionStatusService {

	@Autowired
	private LuggageTransactionStatusDao dao;

	@Override
	public void save(LuggageTransactionStatus luggageTransactionStatus) {
		dao.save(luggageTransactionStatus);
	}

	@Override
	public void saveOrUpdateLuggageTransactionStatus(LuggageTransactionStatus luggageTransactionStatus) {
		dao.saveOrUpdateLuggageTransactionStatus(luggageTransactionStatus);
	}

	@Override
	public LuggageTransactionStatus saveLuggageTransactionStatus(UserDto userDto,
			LuggageActivityStatus luggageActivityStatus, LuggageTransaction luggageTransaction) {
		LuggageTransactionStatus luggageTransactionStatus = new LuggageTransactionStatus();
		luggageTransactionStatus.setLuggageActivityStatus(luggageActivityStatus);
		luggageTransactionStatus.setUserMobile(userDto.getMobileNo());
		Set<LuggageTransaction> luggageTransactionSet = new HashSet<>();
		luggageTransactionSet.add(luggageTransaction);
		luggageTransactionStatus.setLuggageTransactions(luggageTransactionSet);
		dao.save(luggageTransactionStatus);
		return luggageTransactionStatus;
	}

	@Override
	public LuggageTransactionStatus updateLuggageTransactionStatus(LuggageTransactionStatus luggageTransactionStatus,
			LuggageActivityStatus luggageActivityStatus, Set<LuggageTransaction> existingLuggageTransactions,
			LuggageTransaction luggageTransaction) {
		luggageTransactionStatus.setLuggageActivityStatus(luggageActivityStatus);
		existingLuggageTransactions.add(luggageTransaction);
		dao.saveOrUpdateLuggageTransactionStatus(luggageTransactionStatus);
		return luggageTransactionStatus;
	}

	@Override
	public LuggageTransactionStatus findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageTransactionStatus> findAllLuggageTransactionStatuses() {
		return dao.findAllLuggageTransactionStatuses();
	}

	@Override
	public List<LuggageTransactionStatus> findAllLuggageTransactionStatusesForDate(Date expectedDate) {
		return dao.findAllLuggageTransactionStatusesForDate(expectedDate);
	}
}
