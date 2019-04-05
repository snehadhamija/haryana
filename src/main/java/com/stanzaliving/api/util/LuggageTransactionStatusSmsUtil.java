package com.stanzaliving.api.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.service.SMSService;

@Component
public class LuggageTransactionStatusSmsUtil {

	@Autowired
	SMSService smsService;

	public void sendLuggageTransactionStatusSms(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest) {
		try {
			smsService.sendMessage((String) luggageTransactionStatusDto.getUser().get("mobileNo"),
					luggageTransactionStatusDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
