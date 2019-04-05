package com.stanzaliving.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.Gateway.SmsModel;
import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.kafka.KafkaUtil;
import com.stanzaliving.api.model.LuggageActivity;

@Service("smsService")
@Transactional
public class SMSServiceImpl implements SMSService {

	@Autowired
	KafkaUtil kafkaUtil;

	@Autowired
	LuggageCategoryService luggageCategoryService;

	@Autowired
	LuggageActivityService luggageActivityService;

	private final Logger logger = LoggerFactory.getLogger(SMSServiceImpl.class);

	@Override
	public void sendMessage(String mobileNumber, LuggageTransactionStatusDto luggageTransactionStatusDto) {
		sendSms(mobileNumber, smsContent(luggageTransactionStatusDto));
	}

	public String smsContent(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		String smsContent = null;
		Integer activityId = luggageTransactionStatusDto.getLuggageActivityId();
		LuggageActivity luggageActivity = luggageActivityService.findById(activityId);
		String luggageIdsCommaSperated = getLuggageIds(luggageTransactionStatusDto).toString().replace("[", "")
				.replace("]", "").replace(", ", ",");
		smsContent = "Hi Stanzen! You have requested for Luggage " + luggageActivity.getActivityName()
				+ " for luggageIds " + luggageIdsCommaSperated;
		return smsContent;
	}

	public List<String> getLuggageIds(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		List<String> luggageIds = new ArrayList<>();
		for (HashMap<String, Object> luggageSummary : luggageTransactionStatusDto.getLuggageSummary()) {
			String luggageId = (String) luggageSummary.get("luggageId");
			luggageIds.add(luggageId);
		}
		return luggageIds;
	}

	public void sendSms(String mobileNumber, String message) {
		SmsModel sms = new SmsModel(mobileNumber, message);
		kafkaUtil.produceSms(sms);
	}
}
