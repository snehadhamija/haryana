package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.stanzaliving.api.model.LuggageTransactionStatus;
import com.stanzaliving.api.service.LuggageTransactionObjectService;
import com.stanzaliving.api.service.LuggageTransactionStatusService;
import com.stanzaliving.api.util.LuggageChargeUtil;
import com.stanzaliving.api.util.LuggageTransactionDetailUtil;
import com.stanzaliving.api.util.LuggageTransactionStatusUtil;
import com.stanzaliving.api.util.LuggageTransactionUtil;

@RestController
public class LuggageTransactionStatusRestController {

	@Autowired
	LuggageTransactionStatusService luggageTransactionStatusService;

	@Autowired
	LuggageTransactionStatusUtil luggageTransactionStatusUtil;

	@Autowired
	LuggageTransactionUtil luggageTransactionUtil;

	@Autowired
	LuggageTransactionDetailUtil luggageTransactionDetailUtil;

	@Autowired
	LuggageChargeUtil luggageChargeUtil;

	@Autowired
	LuggageTransactionObjectService luggageTransactionObjectService;

	private static Logger logger = LoggerFactory.getLogger(LuggageTransactionStatusRestController.class);

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

	// ----- Save/update luggage transaction status -----
	@RequestMapping(value = "/luggageTransactionStatus", method = RequestMethod.POST)
	public ResponseEntity<Object> saveLuggageTransaction(@RequestBody HashMap<String, Object> request,
			HttpServletRequest httpRequest) {
		try {
			LuggageTransactionStatusDto luggageTransactionStatusDto = new LuggageTransactionStatusDto(request);
			luggageTransactionObjectService.saveOrUpdateLuggageTransactionStatusObject(luggageTransactionStatusDto,
					httpRequest);
		} catch (Exception e) {
			logger.info("Exception caught while hitting post call for luggage deposit activity !");
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
