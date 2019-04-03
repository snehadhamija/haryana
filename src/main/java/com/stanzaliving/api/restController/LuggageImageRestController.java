package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.service.LuggageImageService;

@RestController
@RequestMapping("/luggageImage")
public class LuggageImageRestController {

	@Autowired
	LuggageImageService luggageImageService;

	// ----- Retrieve all luggage images -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageImages() {
		List<LuggageImage> luggageImages = luggageImageService.findAllLuggageImages();
		if (luggageImages.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageImages, HttpStatus.OK);
	}

	// ----- Retrieve luggageImage by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageImageById(@PathVariable("id") int id) {
		LuggageImage luggageImage = luggageImageService.findById(id);
		if (luggageImage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageImage, HttpStatus.OK);
	}
}
