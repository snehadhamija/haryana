package com.stanzaliving.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageActivityStatus;
import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;
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

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	@Autowired
	LuggageOtpDetailService luggageOtpDetailService;

	@Override
	public void saveOrUpdateLuggageTransactionStatusObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest) {
		LuggageTransaction luggageTransaction = luggageTransactionUtil
				.saveLuggageTransactionObject(luggageTransactionStatusDto);
		List<LuggageTransactionDetail> luggageTransactionDetails = luggageTransactionDetailUtil
				.saveLuggageTransactionDetailObject(luggageTransactionStatusDto, luggageTransaction);
		List<LuggageActivityStatus> luggageActivityStatuses = luggageActivityStatusService
				.findAllLuggageActivityStatusesForLuggageActivity(luggageTransaction.getLuggageActivity());
		if (luggageTransactionStatusDto.getLuggageTransactionStatusId() != null
				&& luggageTransactionStatusDto.getLuggageTransactionStatusId() != 0) {
			if (luggageTransactionStatusDto.getLuggageOtpDetailId() != null
					&& luggageTransactionStatusDto.getLuggageOtpDetailId() != 0) {
				LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService
						.findById(luggageTransactionStatusDto.getLuggageOtpDetailId());
				if (luggageOtpDetail != null) {
					luggageOtpDetailService.saveOrUpdateLuggageOtpDetail(luggageOtpDetail, luggageTransactionDetails);
				}
			}
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
				// partial handover or missing
				if (checkIfItemMissing(luggageTransactionStatusDto)
						|| checkIfAlreadyItemMissed(existingLuggageTransactions)) {
					luggageActivityStatusId = 4;
				}
				// update status entry with status partial-handover
				luggageActivityStatusId = 3;
			} else if (bagsRemaining < currentNumberOfBags) {
				// conflict
				// update status entry with status conflict
				luggageActivityStatusId = 4;
			}
			LuggageActivityStatus luggageActivityStatus = luggageActivityStatusService
					.findById(luggageActivityStatusId);
			luggageTransactionStatusService.updateLuggageTransactionStatus(luggageTransactionStatus,
					luggageActivityStatus, existingLuggageTransactions, luggageTransaction);
		} else {
			luggageChargeUtil.saveLuggageChargeObject(luggageTransactionStatusDto, luggageTransaction);
			UserDto userDto = springRestClientService.getUserDtoForOtherUser(httpRequest,
					luggageTransactionStatusDto.getMobileNo());
			LuggageTransactionStatus luggageTransactionStatus = luggageTransactionStatusService
					.saveLuggageTransactionStatus(userDto, luggageActivityStatuses.get(0), luggageTransaction);
		}
	}

	public boolean checkIfItemMissing(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			if (entry.containsKey("isMissing")) {
				return true;
			}
		}
		return false;
	}

	public boolean checkIfAlreadyItemMissed(Set<LuggageTransaction> existingLuggageTransactions) {
		for (LuggageTransaction luggageTransaction : existingLuggageTransactions) {
			List<LuggageTransactionDetail> luggageTransactionDetails = luggageTransactionDetailService
					.findAllLuggageTransactionDetailsForTransaction(luggageTransaction);
			for (LuggageTransactionDetail luggageTransactionDetail : luggageTransactionDetails) {
				if (luggageTransactionDetail.getLuggageStatus().getId() == 2) {
					return true;
				}
			}
		}
		return false;
	}
}
