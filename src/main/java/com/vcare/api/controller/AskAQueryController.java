/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.vcare.api.dto.AskAQueryRequestDTO;
import com.vcare.api.dto.AskAQueryResponseDTO;
import com.vcare.api.model.AskAQuery;
import com.vcare.api.util.AskAQueryUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/askaquery",
		"/askAQuery",
		"/askAquery"
})
public class AskAQueryController {

	@Autowired
	private AskAQueryUtil askAQueryUtil;

	// ----- Get Asked Queries -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAskedQueries(
			@RequestParam(name = "mobileNumber", required = false) String mobileNumber,
			@RequestParam(name = "email", required = false) String email) {
		List<AskAQueryResponseDTO> askAQueryResponseDTOs = askAQueryUtil.getAskAQueryResponseDTOs(mobileNumber, email);
		return !CollectionUtils.isNullOrEmpty(askAQueryResponseDTOs)
				? new ResponseEntity<Object>(askAQueryResponseDTOs, HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	// ----- Save Asked A Query -----
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> saveAskAQuery(
			@RequestBody @Valid AskAQueryRequestDTO askAQueryRequestDTO) {
		AskAQuery askAQuery = askAQueryUtil.saveAskAQueryObject(askAQueryRequestDTO);
		return Objects.nonNull(askAQuery)
				? new ResponseEntity<Object>(askAQueryUtil.convertAskAQueryEntityToAskAQueryResponseDto(askAQuery), HttpStatus.OK)
				: new ResponseEntity<Object>("Something went wrong !", HttpStatus.CONFLICT);
	}

}
