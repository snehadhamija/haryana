/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.stanzaliving.api.model.FAQ;
import com.stanzaliving.api.service.FAQService;
import com.stanzaliving.api.util.FAQUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/faq",
		"/FAQ",
		"fAQ"
})
public class FAQRestController {

	@Autowired
	private FAQService fAQService;

	@Autowired
	private FAQUtil fAQUtil;

	// ----- Get faqs -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllFAQs(
			@RequestParam(name = "isActive", required = false, defaultValue = "true") boolean isActive) {
		List<FAQ> faqs = fAQService.findAllActiveFAQs(isActive);
		return !CollectionUtils.isNullOrEmpty(faqs)
				? new ResponseEntity<Object>(fAQUtil.getSortedFAQResponseDtoList(faqs), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	// ----- Get faqs for sub disease -----
	@RequestMapping(value = "/{subDiseaseId}", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllFAQsForSubDisease(
			@PathVariable(name = "subDiseaseId", required = true) Integer subDiseaseId,
			@RequestParam(name = "isActive", required = false, defaultValue = "true") boolean isActive) {
		List<FAQ> faqs = fAQService.findAllFAQsForSubDisease(isActive, subDiseaseId);
		return !CollectionUtils.isNullOrEmpty(faqs)
				? new ResponseEntity<Object>(fAQUtil.getSortedFAQResponseDtoList(faqs), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
