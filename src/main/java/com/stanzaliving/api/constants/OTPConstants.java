package com.stanzaliving.api.constants;

public class OTPConstants {

	public static final String ALLOWED_CHARACTERS = "123456789";

	public static final int OTP_LENGTH = 4;

	public static final int SECONDS_IN_A_MINUTE = 60;

	public static final int OTP_EXPIRE_AFTER_SECONDS = 120;

	public static final int MAXIMUM_OTP_GENERATE_ATTEMPTS = 3;

	public static final int MAXIMUM_OTP_VERIFICATION_ATTEMPTS = 3;

	public static final int MAX_PERIOD_RESTRICTION = 10;

	public static final int LIMIT_APPLICABLE_PERIOD_IN_SECONDS = MAX_PERIOD_RESTRICTION * 60;

	public static final int MIMIMUM_GAP_BETWEEN_TWO_OTP_GENERATE_REQUESTS_IN_SECONDS = 60;

	public static final String OTP_MESSAGE = "OTP is %s.\nPlease share OTP with RC to proceed with 'LUGGAGE HANDOVER' request within 24 hours.\n";

}
