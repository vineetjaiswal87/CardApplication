/**
 * 
 */
package com.vineet.cc.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vineet.cc.dto.CardDetailRequest;
import com.vineet.cc.exceptions.ErrorResponse;
import com.vineet.cc.model.CardDetails;

/**
 * Declared public API interface for all types of payment Cards
 *
 */
public interface CardInterface {
	
	public ResponseEntity<ErrorResponse> addCardDetails(CardDetailRequest req);
	public List<CardDetails> getAllCardDetails();
	
}
