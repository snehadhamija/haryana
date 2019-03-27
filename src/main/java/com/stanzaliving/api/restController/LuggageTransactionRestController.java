package com.stanzaliving.api.restController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.service.LuggageActivityService;
import com.stanzaliving.api.service.LuggageTransactionService;
import com.stanzaliving.api.util.DateUtil;

@RestController
public class LuggageTransactionRestController {

	@Autowired
	LuggageTransactionService luggageTransactionService;

	@Autowired
	LuggageActivityService luggageActivityService;

	// ----- Retrieve all luggage transactions -----
	@RequestMapping(value = "/luggageTransaction", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageTransactions() {
		List<LuggageTransaction> luggageTransactions = luggageTransactionService.findAllLuggageTransactions();
		if (luggageTransactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageTransactions, HttpStatus.OK);
	}

	// ----- Retrieve luggageTransaction by id -----
	@RequestMapping(value = "/luggageTransaction/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageTransactionById(@PathVariable("id") int id) {
		LuggageTransaction luggageTransaction = luggageTransactionService.findById(id);
		if (luggageTransaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageTransaction, HttpStatus.OK);
	}

	// ----- Retrieve all luggage transactions for a date -----
	@RequestMapping(value = "/luggageTransaction/date", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageTransactionsForDate(
			@RequestParam(value = "expectedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expectedDate) {
		if (expectedDate == null) {
			expectedDate = DateUtil.getFormatedCleanDate(DateUtil.customiseDateTime(new Date(), 0, 0, 0), "yyyy-MM-dd");
		}
		List<LuggageTransaction> luggageTransactions = luggageTransactionService
				.findAllLuggageTransactionsForDate(expectedDate);
		if (luggageTransactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageTransactions, HttpStatus.OK);
	}
}
