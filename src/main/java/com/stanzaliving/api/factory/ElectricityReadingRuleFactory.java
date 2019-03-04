package com.stanzaliving.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.rule.AbstractRule;

@Configuration
public class ElectricityReadingRuleFactory {

	private static Logger logger = LoggerFactory.getLogger(ElectricityReadingRuleFactory.class);

	public AbstractRule getRuleByName(String ruleName) {
		AbstractRule rule;
		switch (ruleName) {
		// case "is-notification-active-for-user":
		// rule = userNotificationDisabledRule;
		// break;
		// case "hostel-all-devices-online":
		// rule = hostelDeviceRule;
		// break;
		// case "is-notification-active-for-hostel":
		// rule = hostelNotificationActiveRule;
		// break;
		// case "late-night-for-notification":
		// rule = lateNightNotificationRule;
		// break;
		default:
			rule = null;
		}
		return rule;
	}

	public boolean runRule(String type, ElectricityMeterDetails electricityMeterDetails) {
		AbstractRule rule = getRuleByName(type);
		if (rule == null) {
			logger.warn("Rule:{} not found", type);
			return false;
		}
		rule.run(electricityMeterDetails);
		return rule.isPassed();
	}

}
