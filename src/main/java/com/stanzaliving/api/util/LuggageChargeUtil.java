package com.stanzaliving.api.util;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.model.LuggagePaymentMode;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.service.LuggageChargeService;
import com.stanzaliving.api.service.LuggagePaymentModeService;

@Component
public class LuggageChargeUtil {

	@Autowired
	LuggagePaymentModeService luggagePaymentModeService;

	@Autowired
	LuggageChargeService luggageChargeService;

	public void saveLuggageChargeObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			LuggageTransaction luggageTransaction) {
		String amount = luggageTransactionStatusDto.getAmount();
		Double smaountDouble = Double.valueOf(amount);
		DecimalFormat decimalFormat = new DecimalFormat(".##");
		Double multipliedValueRounded = Double.valueOf(decimalFormat.format(smaountDouble));
		String amountString = multipliedValueRounded.toString();
		Integer luggagePaymentModeId = luggageTransactionStatusDto.getLuggagePaymentModeId();
		LuggagePaymentMode luggagePaymentMode = luggagePaymentModeService.findById(luggagePaymentModeId);
		LuggageCharge luggageCharge = luggageChargeService.saveLuggageCharge(amountString, luggagePaymentMode,
				luggageTransaction);
	}
}
