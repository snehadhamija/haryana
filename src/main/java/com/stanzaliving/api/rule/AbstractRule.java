package com.stanzaliving.api.rule;

import com.stanzaliving.api.model.ElectricityMeterDetails;

public abstract class AbstractRule {

	private boolean passed;

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public abstract void run(ElectricityMeterDetails electricityMeterDetails);

}
