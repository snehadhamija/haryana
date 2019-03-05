package com.stanzaliving.api.factory;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.stanzaliving.api.rule.AbstractRule;
import com.stanzaliving.api.rule.BasicRule;
import com.stanzaliving.api.rule.MinimumReadingCriteriaRule;

@Configuration
public class ElectricityReadingRuleFactory {

	private static Logger logger = LoggerFactory.getLogger(ElectricityReadingRuleFactory.class);

	@Autowired
	BasicRule basicRule;

	@Autowired
	MinimumReadingCriteriaRule minimumReadingCriteriaRule;

	public AbstractRule getRuleByName(String ruleName) {
		AbstractRule rule;
		switch (ruleName) {
		case "basic-rule":
			rule = basicRule;
			break;
		case "minimum-reading-criteria-rule":
			rule = minimumReadingCriteriaRule;
			break;
		default:
			rule = null;
		}
		return rule;
	}

	public boolean runRule(String type, HashMap<String, Object> entry) {
		AbstractRule rule = getRuleByName(type);
		if (rule == null) {
			logger.warn("Rule:{} not found", type);
			return false;
		}
		rule.run(entry);
		return rule.isPassed();
	}

}
