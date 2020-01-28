package com.vcare.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EmailConfig {

    public static String PROFILE_EMAIL_FROM_ADDRESS = "Stanza Profile<profile@mail.stanzaliving.in>";

    public static String PROFILE_EMAIL_TO_USER_SUBJECT = "Profile update requested for Stanza account";

    public static String PROFILE_EMAIL_TO_TEAM_SUBJECT = "Profile update requested by ";

    public static String PROFILE_EMAIL_APPROVAL_SUBJECT = "Profile update approved";

    public static String PROFILE_EMAIL_REJECTED_SUBJECT = "Profile update rejected";

    public static final HashMap<String, ArrayList> TEAM_RECIPIENTS = new HashMap<String, ArrayList>();

    static {
        TEAM_RECIPIENTS.put("PROFILE_HANDLE_TEAM",
                new ArrayList<>(
                        Arrays.asList(
                                "help@stanzaliving.com"
                        //                    "neeraj.verma@stanzaliving.com"
                        )
                )
        );
        TEAM_RECIPIENTS.put("MISSING_MENU_MAIL_RECIPIENTS",
                new ArrayList<>(
                        Arrays.asList(
                                "shailendra.singh@stanzaliving.com",
                                "sahil.ludhani@stanzaliving.com",
                                "rohit.khatri@stanzaliving.com",
				"mukesh.sinha@stanzaliving.com",
				"rajat.jain@stanzaliving.com"
//                                "neeraj.verma@stanzaliving.com"
                        )
                )
        );
    }

    public static String HTML_MAIL_CONTENT_TYPE = "text/html";

    public static String PLAIN_TEXT_CONTENT_TYPE = "text/plain";

}
