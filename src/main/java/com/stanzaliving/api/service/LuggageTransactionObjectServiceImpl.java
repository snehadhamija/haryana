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
import com.stanzaliving.api.model.LuggageLifecycle;
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

	@Autowired
	LuggageLifecycleService luggageLifecycleService;

	public Object areDuplicateLuggageIdsPresent(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			String luggageId = (String) entry.get("luggageId");
			List<LuggageLifecycle> luggageLifecycle = luggageLifecycleService
					.findAllLuggageLifecyclesForLuggageId(luggageId);
			if (!luggageLifecycle.isEmpty()) {
				return luggageId;
			}
		}
		return null;
	}

	public boolean isDepositActivity(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		if (luggageTransactionStatusDto.getLuggageActivityId() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Object checkExistanceOfLuggageIdInSystem(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		if (isDepositActivity(luggageTransactionStatusDto)
				&& areDuplicateLuggageIdsPresent(luggageTransactionStatusDto) != null) {
			return areDuplicateLuggageIdsPresent(luggageTransactionStatusDto);
		}
		return null;
	}

	@Override
	public void saveOrUpdateLuggageTransactionStatusObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest) {
		LuggageTransaction luggageTransaction = luggageTransactionUtil
				.saveLuggageTransactionObject(luggageTransactionStatusDto);
		List<LuggageTransactionDetail> luggageTransactionDetails = luggageTransactionDetailUtil
				.saveLuggageTransactionDetailObject(luggageTransactionStatusDto, luggageTransaction);
		if (luggageTransactionStatusDto.getLuggageTransactionStatusId() != null
				&& luggageTransactionStatusDto.getLuggageTransactionStatusId() != 0) {
			updateExistingLuggageTransactionStatus(luggageTransactionStatusDto, luggageTransactionDetails,
					luggageTransaction);
		} else {
			saveLuggageTransactionStatus(luggageTransactionStatusDto, httpRequest, luggageTransaction);
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
				LuggageLifecycle luggageLifecycle = luggageLifecycleService
						.findLuggageLifecycleForLuggageTransactionDetail(luggageTransactionDetail);
				if (luggageLifecycle.getLuggageStatus().getId() == 2) {
					return true;
				}
			}
		}
		return false;
	}

	public Integer calculateBagRemaining(Set<LuggageTransaction> existingLuggageTransactions) {
		Integer bagsRemaining = 0;
		for (LuggageTransaction transaction : existingLuggageTransactions) {
			if (transaction.getLuggageActivity().getId() == 1) {
				bagsRemaining = bagsRemaining + transaction.getNumberOfBags();
			} else {
				bagsRemaining = bagsRemaining - transaction.getNumberOfBags();
			}
		}
		return bagsRemaining;
	}

	public LuggageActivityStatus decideLuggageActivityStatus(LuggageTransactionStatusDto luggageTransactionStatusDto,
			LuggageTransactionStatus luggageTransactionStatus, Set<LuggageTransaction> existingLuggageTransactions,
			LuggageTransaction luggageTransaction) {
		Integer bagsRemaining = calculateBagRemaining(existingLuggageTransactions);
		Integer currentNumberOfBags = luggageTransaction.getNumberOfBags();
		Integer luggageActivityStatusId = decideLuggageActivityStatusId(bagsRemaining, currentNumberOfBags,
				luggageTransactionStatusDto, existingLuggageTransactions);
		LuggageActivityStatus luggageActivityStatus = luggageActivityStatusService.findById(luggageActivityStatusId);
		return luggageActivityStatus;
	}

	public Integer decideLuggageActivityStatusId(Integer bagsRemaining, Integer currentNumberOfBags,
			LuggageTransactionStatusDto luggageTransactionStatusDto,
			Set<LuggageTransaction> existingLuggageTransactions) {
		Integer luggageActivityStatusId = 0;
		if (bagsRemaining == currentNumberOfBags) {
			luggageActivityStatusId = 2;
		} else if (bagsRemaining > currentNumberOfBags) {
			if (checkIfItemMissing(luggageTransactionStatusDto)
					|| checkIfAlreadyItemMissed(existingLuggageTransactions)) {
				luggageActivityStatusId = 4;
			} else {
				luggageActivityStatusId = 3;
			}
		} else if (bagsRemaining < currentNumberOfBags) {
			luggageActivityStatusId = 5;
		}
		return luggageActivityStatusId;
	}

	public void saveOrUpdateLuggageOtpDetail(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails) {
		if (luggageTransactionStatusDto.getLuggageOtpDetailId() != null
				&& luggageTransactionStatusDto.getLuggageOtpDetailId() != 0) {
			LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService
					.findById(luggageTransactionStatusDto.getLuggageOtpDetailId());
			if (luggageOtpDetail != null) {
				luggageOtpDetailService.saveOrUpdateLuggageOtpDetail(luggageOtpDetail, luggageTransactionDetails);
			}
		}
	}

	public void updateExistingLuggageTransactionStatus(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails, LuggageTransaction luggageTransaction) {
		saveOrUpdateLuggageOtpDetail(luggageTransactionStatusDto, luggageTransactionDetails);
		LuggageTransactionStatus luggageTransactionStatus = luggageTransactionStatusService
				.findById(luggageTransactionStatusDto.getLuggageTransactionStatusId());
		Set<LuggageTransaction> existingLuggageTransactions = luggageTransactionStatus.getLuggageTransactions();
		LuggageActivityStatus luggageActivityStatus = decideLuggageActivityStatus(luggageTransactionStatusDto,
				luggageTransactionStatus, existingLuggageTransactions, luggageTransaction);
		luggageTransactionStatusService.updateLuggageTransactionStatus(luggageTransactionStatus, luggageActivityStatus,
				existingLuggageTransactions, luggageTransaction);
	}

	public void saveLuggageTransactionStatus(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest, LuggageTransaction luggageTransaction) {
		luggageChargeUtil.saveLuggageChargeObject(luggageTransactionStatusDto, luggageTransaction);
		UserDto userDto = springRestClientService.getUserDtoForOtherUser(httpRequest,
				luggageTransactionStatusDto.getMobileNo());
		List<LuggageActivityStatus> luggageActivityStatuses = luggageActivityStatusService
				.findAllLuggageActivityStatusesForLuggageActivity(luggageTransaction.getLuggageActivity());
		luggageTransactionStatusService.saveLuggageTransactionStatus(userDto, luggageActivityStatuses.get(0),
				luggageTransaction);
	}
}
