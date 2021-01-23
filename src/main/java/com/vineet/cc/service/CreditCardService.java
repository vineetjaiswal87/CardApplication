package com.vineet.cc.service;

import static com.vineet.cc.exceptions.ErrorResponse.of;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vineet.cc.constants.ErrorMessage;
import com.vineet.cc.dto.CardDetailRequest;
import com.vineet.cc.exceptions.ErrorResponse;
import com.vineet.cc.exceptions.InvalidCardException;
import com.vineet.cc.exceptions.InvalidInputException;
import com.vineet.cc.model.CardDetails;
import com.vineet.cc.repository.CardDetailsRepository;

/**
 * Class implementing the CardInterface to perform card addition and fetch from DB.
 *
 */

@Service
public class CreditCardService implements CardInterface {
	Logger logger = LoggerFactory.getLogger(CreditCardService.class);
	
	@Autowired
	CardDetailsRepository crudRepo;
	
	@Autowired
	CreditCardNumberValidatorImpl cardNumberValidator;
	
	/** It validates the request and then adds the data in the database.
	 * @param req contains the card details to be added
	 * @return ResponseEntity object.
	 */
	public ResponseEntity<ErrorResponse> addCardDetails(CardDetailRequest req) {
		try {
			cardNumberValidator.validate(req.getCardNumber());
		} catch (InvalidInputException|InvalidCardException ex) {
			logger.error("Invalid input::" +ex.getMessage());
			return new ResponseEntity<>(of(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		
		if (crudRepo.existsBycardNum(req.getCardNumber())) {
			logger.error("Card already exists in DB");
			return new ResponseEntity<>(of(ErrorMessage.CARD_ALREADY_PRESENT, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		
		try {
			crudRepo.save(new CardDetails(req.getName(), req.getCardNumber(), new BigDecimal(req.getLimitAmt()), req.getUserId()));
			logger.info("Card Details added successfully");
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {
			logger.error("Card Addition failed in DB - ", ex.getMessage());
			return new ResponseEntity<>(of(ErrorMessage.CARD_ADDITION_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/** It fetches all the records from the database.
	 * @return List of Card details.
	 */
	public List<CardDetails> getAllCardDetails() {
		try {
			return crudRepo.findAll();
		} catch(Exception ex) {
			logger.error("Unable to fetch data - ", ex.getMessage());
			throw ex;
		}
		
		
	}

}
