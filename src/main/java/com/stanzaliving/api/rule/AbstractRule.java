package com.stanzaliving.api.rule;

import java.util.HashMap;

public abstract class AbstractRule {

	private boolean passed;

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public abstract void run(HashMap<String, Object> entry);

}
