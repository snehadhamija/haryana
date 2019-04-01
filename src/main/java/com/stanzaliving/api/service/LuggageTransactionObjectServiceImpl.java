package com.stanzaliving.api.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageActivityStatus;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionStatus;
import com.stanzaliving.api.util.LuggageChargeUtil;
import com.stanzaliving.api.util.LuggageTransactionDetailUtil;
import com.stanzaliving.api.util.LuggageTransactionUtil;

@Service("luggageTransactionObjectService")
@Transactional
public class LuggageTransactionObjectServiceImpl implements LuggageTransactionObjectService {

	@Autowired
	LuggageTransactionUtil luggageTransactionUtil;

	@Autowired
	LuggageTransactionDetailUtil luggageTransactionDetailUtil;

	@Autowired
	LuggageChargeUtil luggageChargeUtil;

	@Autowired
	LuggageActivityStatusService luggageActivityStatusService;

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	LuggageTransactionStatusService luggageTransactionStatusService;

	@Override
	public void saveOrUpdateLuggageTransactionStatusObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest) {
		LuggageTransaction luggageTransaction = luggageTransactionUtil
				.saveLuggageTransactionObject(luggageTransactionStatusDto);
		luggageTransactionDetailUtil.saveLuggageTransactionDetailObject(luggageTransactionStatusDto,
				luggageTransaction);
		List<LuggageActivityStatus> luggageActivityStatuses = luggageActivityStatusService
				.findAllLuggageActivityStatusesForLuggageActivity(luggageTransaction.getLuggageActivity());
		if (luggageTransactionStatusDto.getLuggageTransactionStatusId() != null
				&& luggageTransactionStatusDto.getLuggageTransactionStatusId() != 0) {
			LuggageTransactionStatus luggageTransactionStatus = luggageTransactionStatusService
					.findById(luggageTransactionStatusDto.getLuggageTransactionStatusId());
			Set<LuggageTransaction> existingLuggageTransactions = luggageTransactionStatus.getLuggageTransactions();
			Integer bagsRemaining = 0;
			for (LuggageTransaction transaction : existingLuggageTransactions) {
				if (transaction.getLuggageActivity().getId() == 1) {
					bagsRemaining = bagsRemaining + transaction.getNumberOfBags();
				} else {
					bagsRemaining = bagsRemaining - transaction.getNumberOfBags();
				}
			}
			Integer currentNumberOfBags = luggageTransaction.getNumberOfBags();
			// rules
			Integer luggageActivityStatusId = 0;
			if (bagsRemaining == currentNumberOfBags) {
				// cycle completed !
				// update status entry with status handover
				luggageActivityStatusId = 2;
			} else if (bagsRemaining > currentNumberOfBags) {
				// partial handover
				// update status entry with status partial-handover
				luggageActivityStatusId = 3;
			} else if (bagsRemaining < currentNumberOfBags) {
				// conflict
				// update status entry with status conflict
				luggageActivityStatusId = 4;
			}
			LuggageActivityStatus luggageActivityStatus = luggageActivityStatusService
					.findById(luggageActivityStatusId);
			// update entry in transaction status table
			System.out.println("==========final data===========");
			System.out.println(luggageTransactionStatus.getLuggageActivityStatus().getStatusName());
			luggageTransactionStatusService.updateLuggageTransactionStatus(luggageTransactionStatus,
					luggageActivityStatus, existingLuggageTransactions, luggageTransaction);
			System.out.println(currentNumberOfBags);
			System.out.println(bagsRemaining);
			System.out.println(luggageActivityStatusId);
			System.out.println(luggageActivityStatus);
			System.out.println(luggageTransactionStatus.getLuggageActivityStatus().getStatusName());
			System.out.println("==========final data===========");
		} else {
			luggageChargeUtil.saveLuggageChargeObject(luggageTransactionStatusDto, luggageTransaction);
			UserDto userDto = springRestClientService.getUserDtoForOtherUser(httpRequest,
					luggageTransactionStatusDto.getMobileNo());
			LuggageTransactionStatus luggageTransactionStatus = luggageTransactionStatusService
					.saveLuggageTransactionStatus(userDto, luggageActivityStatuses.get(0), luggageTransaction);
		}
	}
}
