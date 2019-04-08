package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.stanzaliving.api.Gateway.EmailModel;
import com.stanzaliving.api.Gateway.GatewayUtil;
import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.model.LuggageCategory;
import com.stanzaliving.api.service.LuggageActivityService;
import com.stanzaliving.api.service.LuggageCategoryService;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageTransactionStatusEmailUtil {

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	GatewayUtil gatewayUtil;

	@Autowired
	LuggageActivityService luggageActivityService;

	@Autowired
	LuggageCategoryService luggageCategoryService;

	public final String LUGGAGE_MAILER = "luggage@stanzaliving.com";

	public void sendLuggageTransactionStatusEmail(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest) {
		UserDto sentToUser = springRestClientService.getUserDtoForOtherUser(httpRequest,
				(String) luggageTransactionStatusDto.getUser().get("mobileNo"));
		String sentToUserEmail = sentToUser.getEmail();
		ArrayList<String> mailersList = new ArrayList<>();
		mailersList.add(sentToUserEmail);
		String emailContent = luggage_email_table(luggageTransactionStatusDto);
		EmailModel em = new EmailModel(LUGGAGE_MAILER, "Stanza Luggage", mailersList, emailContent, null);
		em.setContentType(EmailConfig.HTML_MAIL_CONTENT_TYPE);
		gatewayUtil.SendEmail(em);
	}

	public Multimap<String, Object> populateMultiMap(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		Multimap<String, Object> myHashMultimap = HashMultimap.create();
		for (HashMap<String, Object> luggageSummary : luggageTransactionStatusDto.getLuggageSummary()) {
			Integer luggageCategoryId = (Integer) luggageSummary.get("luggageCategory");
			LuggageCategory luggageCategory = luggageCategoryService.findById(luggageCategoryId);
			String weight = (String) luggageSummary.get("weight");
			String luggageId = (String) luggageSummary.get("luggageId");
			myHashMultimap.put(" LuggageId", luggageId);
			myHashMultimap.put(" Luggage Category", luggageCategory.getCategoryName());
			myHashMultimap.put(" Weight", weight);
		}
		return myHashMultimap;
	}

	public List<HashMap<String, Object>> populateHashMap(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		List<HashMap<String, Object>> hashMaps = new ArrayList<>();
		for (HashMap<String, Object> luggageSummary : luggageTransactionStatusDto.getLuggageSummary()) {
			HashMap<String, Object> hashMap = new HashMap<>();
			Integer luggageCategoryId = (Integer) luggageSummary.get("luggageCategory");
			LuggageCategory luggageCategory = luggageCategoryService.findById(luggageCategoryId);
			String weight = (String) luggageSummary.get("weight");
			String luggageId = (String) luggageSummary.get("luggageId");
			hashMap.put(" LuggageId", luggageId);
			hashMap.put(" Luggage Category", luggageCategory.getCategoryName());
			hashMap.put(" Weight", weight);
			hashMaps.add(hashMap);
		}
		for (HashMap<String, Object> hashMap : hashMaps) {
			System.out.println(hashMap.keySet());
			System.out.println(hashMap.values());
		}
		return hashMaps;
	}

	public String createLuggageDataTableUsingHashMap(List<HashMap<String, Object>> hashMaps) {
		String luggageDataTable = "";
		if (!hashMaps.isEmpty()) {
			luggageDataTable += "<tr>";
			for (String key : hashMaps.get(0).keySet()) {
				luggageDataTable += "<th>" + key + "</th>";
			}
			luggageDataTable += "</tr>";
			for (HashMap<String, Object> hm : hashMaps) {
				luggageDataTable += "<tr>";
				for (Object o : hm.values()) {
					luggageDataTable += "<td align=center style=\"color:blue\">" + o + "</td>";
				}
				luggageDataTable += "</tr>";
			}
		}
		return luggageDataTable;
	}

	public String createLuggageDataTable(List<Multimap<String, Object>> luggageHashMapList) {
		String luggageDataTable = "";
		if (!luggageHashMapList.isEmpty()) {
			luggageDataTable += "<tr>";
			for (String key : luggageHashMapList.get(0).keySet()) {
				luggageDataTable += "<th>" + key + "</th>";
			}
			luggageDataTable += "</tr>";
			for (Multimap<String, Object> mp : luggageHashMapList) {
				luggageDataTable += "<tr>";
				for (Object o : mp.values()) {
					luggageDataTable += "<td align=center style=\"color:blue\">" + o + "</td>";
				}
				luggageDataTable += "</tr>";
			}
		}
		return luggageDataTable;
	}

	public String luggage_email_content(LuggageTransactionStatusDto luggageTransactionStatusDto,
			String luggageDataTable) {
		Integer activityId = luggageTransactionStatusDto.getLuggageActivityId();
		LuggageActivity luggageActivity = luggageActivityService.findById(activityId);
		String content = "";
		String food_menu_approver_email_content = "";
		food_menu_approver_email_content = new StringBuilder()
				.append("Dear Stanzen," + "<br><br>Greetings from Stanza Living!")
				.append("<br><br>You have requested for the Luggage " + luggageActivity.getActivityName())
				.append(". Details of request are mentioned below:\n")
				.append("<br><br><table border=2  rules=columns cellpadding=15 cellspacing=0 style=text-align: center>\n")
				.append("<tr><th colspan=4>" + luggageActivity.getActivityName() + " data</th></tr>")
				.append(luggageDataTable).append("</table>").append("<br><br>Regards<br>Stanza Operations Team</html>")
				.toString();
		content = food_menu_approver_email_content;
		return content;
	}

	public String luggage_email_table(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		String luggageDataTable = null;
		List<Multimap<String, Object>> luggageHashMapList = new ArrayList<>();
		List<HashMap<String, Object>> hashMaps = populateHashMap(luggageTransactionStatusDto);
		Multimap<String, Object> luggageHashMap = populateMultiMap(luggageTransactionStatusDto);
		luggageHashMapList.add(luggageHashMap);
		luggageDataTable = createLuggageDataTableUsingHashMap(hashMaps);
		// luggageDataTable = createLuggageDataTable(luggageHashMapList);
		String luggage_email_content = luggage_email_content(luggageTransactionStatusDto, luggageDataTable);
		return luggage_email_content;
	}

}
