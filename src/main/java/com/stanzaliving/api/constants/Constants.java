package com.stanzaliving.api.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String CHECKIN_MAILID = "STANZACHECKIN@GMAIL.COM";
	public static final String GET = "get";
	public static final String POST = "post";
	public static final String PUT = "put";
	public static final String FRESHDESK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
	public static final String ASSOCIATED_USER = "Room";
	public static final String COREUSERFETCHURL = "https://core.stanzaliving.com/core/userdto/mobile/";
	public static final String CORESERVERURL = "https://core.stanzaliving.com/core/";
	public static final String COMPLAINTBETAURL = "https://betacore.stanzaliving.com/complaint/";
	public static final String COMPLAINTPRODURL = "https://core.stanzaliving.com/complaint/";
	public static final String DEFAULTCREDENTIALS = "9906000101:NIPU9906";
	public static final List<String> ELECTRICITY_READING_RULES = Arrays.asList("basic-rule", "multiple-criteria-rule",
			"average-criteria-rule");
	public static final String MINIMUM_READING_CRITERIA_VALUE = "30";
	public static final String MULTIPLE_CRITERIA_VALUE = "9";
	public static final String AVERAGE_CRITERIA_PERCENTAGE_VALUE = "200";
	public static final String AVERAGE_CRITERIA_AVERAGE_VALUE = "7";
	public static final Integer MAXIMUM_TOTAL_BOXES_VALUE = 6;
}
