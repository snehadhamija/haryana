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
import com.stanzaliving.api.util.LuggageTransactionStatusUtil;
import com.stanzaliving.api.util.LuggageTransactionUtil;

@RestController
@RequestMapping("/luggageTransactionStatus")
public class LuggageTransactionStatusRestController {

	@Autowired
	LuggageTransactionStatusService luggageTransactionStatusService;

	@Autowired
	LuggageTransactionStatusUtil luggageTransactionStatusUtil;

	@Autowired
	LuggageTransactionObjectService luggageTransactionObjectService;

	@Autowired
	LuggageTransactionUtil luggageTransactionUtil;

	private static Logger logger = LoggerFactory.getLogger(LuggageTransactionStatusRestController.class);

	// ----- Retrieve all luggage transaction statuses -----
	@RequestMapping(value = "", method = RequestMethod.GET)
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/date", method = RequestMethod.GET)
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
	// sample request payload
	// {
	// "luggageSummary":[{"luggageCategory": 1,"luggageId":
	// "LB0SA200","weight":"3.0","luggageImages":["abc","def"]},{"luggageCategory":1,"luggageId":"LB0SA201","weight":"2.0","luggageImages":["ghi"]},{"luggageCategory":1,"luggageId":"LB0SA202","weight":"5.5","luggageImages":[]}],
	// "expectedDate":"2019-03-31 00:00:00",
	// "luggageActivity":1,
	// "luggageStorageRoom":1,
	// "totalBoxes":3,
	// "amount":"250.00",
	// "luggagePaymentMode":1,
	// "user":{"mobileNo":"9906000101"}}
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> saveLuggageTransaction(@RequestBody HashMap<String, Object> request,
			HttpServletRequest httpRequest) {
		try {
			LuggageTransactionStatusDto luggageTransactionStatusDto = new LuggageTransactionStatusDto(request);
			if (luggageTransactionUtil.checkExistanceOfLuggageIdInSystem(luggageTransactionStatusDto) == null) {
				luggageTransactionObjectService.saveOrUpdateLuggageTransactionStatusObject(luggageTransactionStatusDto,
						httpRequest);
			} else {
				Object luggageId = luggageTransactionUtil
						.checkExistanceOfLuggageIdInSystem(luggageTransactionStatusDto);
				return new ResponseEntity<Object>("LuggageId " + luggageId + " already submitted!",
						HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			logger.info("Exception caught while hitting post call for luggage deposit activity !");
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
