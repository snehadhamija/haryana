package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.service.LuggageTransactionService;

@RestController
public class LuggageTransactionRestController {

	@Autowired
	LuggageTransactionService luggageTransactionService;

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
}
