package com.vineet.cc.controller;

import static com.vineet.cc.exceptions.ErrorResponse.of;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vineet.cc.constants.ErrorMessage;
import com.vineet.cc.dto.CardDetailRequest;
import com.vineet.cc.exceptions.ErrorResponse;
import com.vineet.cc.model.CardDetails;
import com.vineet.cc.service.CreditCardService;

@RestController
@RequestMapping("/card")
public class CardController {

	Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	CreditCardService service;

	/** Service to add card details provided by user
	 * @param req contains the card details to be added
	 * @return
	 */
	@PostMapping(value = "/v1/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ErrorResponse> addCardDetails(@RequestBody CardDetailRequest req) {
		logger.info("Add Card Request::" + req.toString());
		return service.addCardDetails(req);
	}

	/** Service to get all the added card details.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllCardDetails() {
		logger.info("Inside getAll service call");
		List<CardDetails> cardDtlList = service.getAllCardDetails();

		if (cardDtlList == null || cardDtlList.isEmpty()) {
			return new ResponseEntity<>(of(ErrorMessage.NO_DATA_FOUND, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(service.getAllCardDetails());
	}

}
