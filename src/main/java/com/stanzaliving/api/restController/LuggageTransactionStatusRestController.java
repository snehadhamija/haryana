package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.model.LuggageCategory;
import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.model.LuggagePaymentMode;
import com.stanzaliving.api.model.LuggageStoreRoom;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.model.LuggageTransactionStatus;
import com.stanzaliving.api.service.LuggageActivityService;
import com.stanzaliving.api.service.LuggageCategoryService;
import com.stanzaliving.api.service.LuggageChargeService;
import com.stanzaliving.api.service.LuggageImageService;
import com.stanzaliving.api.service.LuggagePaymentModeService;
import com.stanzaliving.api.service.LuggageStoreRoomService;
import com.stanzaliving.api.service.LuggageTransactionDetailService;
import com.stanzaliving.api.service.LuggageTransactionService;
import com.stanzaliving.api.service.LuggageTransactionStatusService;
import com.stanzaliving.api.util.LuggageTransactionStatusUtil;

@RestController
public class LuggageTransactionStatusRestController {

	@Autowired
	LuggageTransactionStatusService luggageTransactionStatusService;

	@Autowired
	LuggageTransactionStatusUtil luggageTransactionStatusUtil;

	@Autowired
	LuggageTransactionService luggageTransactionService;

	@Autowired
	LuggageActivityService luggageActivityService;

	@Autowired
	LuggagePaymentModeService luggagePaymentModeService;

	@Autowired
	LuggageStoreRoomService luggageStoreRoomService;

	@Autowired
	LuggageCategoryService luggageCategoryService;

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	@Autowired
	LuggageImageService luggageImageService;

	@Autowired
	LuggageChargeService luggageChargeService;

	// ----- Retrieve all luggage transaction statuses -----
	@RequestMapping(value = "/luggageTransactionStatus", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageTransactionStatuses(HttpServletRequest request,
			@RequestParam(value = "expectedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expectedDate) {
		List<LuggageTransactionStatus> luggageTransactionStatuses = new ArrayList<>();
		if (expectedDate == null) {
			luggageTransactionStatuses = luggageTransactionStatusService.findAllLuggageTransactionStatuses();
		} else {
			luggageTransactionStatuses = luggageTransactionStatusService
					.findAllLuggageTransactionStatusesForDate(expectedDate);
		}
		if (luggageTransactionStatuses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		Object hashMaps = luggageTransactionStatusUtil.createHashMapListForStatuses(request,
				luggageTransactionStatuses);
		return new ResponseEntity<Object>(hashMaps, HttpStatus.OK);
	}

	// ----- Retrieve luggageTransactionStatus by id -----
	@RequestMapping(value = "/luggageTransactionStatus/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageTransactionStatusById(HttpServletRequest request,
			@PathVariable("id") int id) {
		LuggageTransactionStatus luggageTransactionStatus = luggageTransactionStatusService.findById(id);
		if (luggageTransactionStatus == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Object statusHashMaps = luggageTransactionStatusUtil.createHashMapForStatus(request, luggageTransactionStatus);
		return new ResponseEntity<Object>(statusHashMaps, HttpStatus.OK);
	}

	// ----- Retrieve all luggage transactions for a date -----
	@RequestMapping(value = "/luggageTransactionStatus/date", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageTransactionsForDate(HttpServletRequest request,
			@RequestParam(value = "expectedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expectedDate) {
		List<LuggageTransactionStatus> luggageTransactionStatuses = new ArrayList<>();
		if (expectedDate == null) {
			luggageTransactionStatuses = luggageTransactionStatusService.findAllLuggageTransactionStatuses();
		} else {
			luggageTransactionStatuses = luggageTransactionStatusService
					.findAllLuggageTransactionStatusesForDate(expectedDate);
		}
		if (luggageTransactionStatuses.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		Object hashMaps = luggageTransactionStatusUtil.createHashMapListForStatuses(request,
				luggageTransactionStatuses);
		return new ResponseEntity<Object>(hashMaps, HttpStatus.OK);
	}

	// ----- Save a luggage transaction -----
	@RequestMapping(value = "/luggageTransactionStatus", method = RequestMethod.POST)
	public ResponseEntity<Object> saveLuggageTransaction(@RequestBody HashMap<String, Object> request,
			HttpServletRequest httpRequest) {
		LuggageTransactionStatusDto luggageTransactionStatusDto = new LuggageTransactionStatusDto(request);
		Double totalWeightDouble = 0.00;
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			System.out.println(entry);
			String weight = (String) entry.get("weight");
			Double weightDouble = Double.valueOf(weight);
			totalWeightDouble = totalWeightDouble + weightDouble;
		}
		String totalWeight = totalWeightDouble.toString();
		// save entries in transaction table
		Integer totalBoxes = luggageTransactionStatusDto.getTotalBoxes();
		Integer luggageStorageRoomId = luggageTransactionStatusDto.getLuggageStorageRoomId();
		LuggageStoreRoom luggageStoreRoom = luggageStoreRoomService.findById(luggageStorageRoomId);
		String userMobile = luggageTransactionStatusDto.getMobileNo();
		String amount = luggageTransactionStatusDto.getAmount();
		Integer luggageActivityId = luggageTransactionStatusDto.getLuggageActivityId();
		LuggageActivity luggageActivity = luggageActivityService.findById(luggageActivityId);
		Integer luggagePaymentModeId = luggageTransactionStatusDto.getLuggagePaymentModeId();
		LuggagePaymentMode luggagePaymentMode = luggagePaymentModeService.findById(luggagePaymentModeId);
		Date expectedDate = luggageTransactionStatusDto.getExpectedDate();

		LuggageTransaction luggageTransaction = luggageTransactionService.saveLuggageTransaction(luggageActivity,
				userMobile, totalBoxes, totalWeight, expectedDate, luggageStoreRoom);

		// save entry in luggage detail table
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			System.out.println(entry);
			String weight = (String) entry.get("weight");
			String luggageId = (String) entry.get("luggageId");
			Integer luggageCategoryId = (Integer) entry.get("luggageCategory");
			LuggageCategory luggageCategory = luggageCategoryService.findById(luggageCategoryId);
			LuggageTransactionDetail luggageTransactionDetail = luggageTransactionDetailService
					.saveLuggageTransactionDetail(luggageId, weight, luggageCategory, luggageTransaction);
			List<String> luggageImages = (List<String>) entry.get("luggageImages");
			// save entry in image table
			luggageImages.forEach(imageUrl -> {
				LuggageImage luggageImage = luggageImageService.saveLuggageImage(luggageTransactionDetail, imageUrl);
			});
		}
		// save entry in transaction status table
		// save entry in charge table
		LuggageCharge luggageCharge = luggageChargeService.saveLuggageCharge(amount, luggagePaymentMode,
				luggageTransaction);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
