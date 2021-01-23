/**
 * 
 */
package com.vineet.cc.service;

import com.vineet.cc.exceptions.InvalidCardException;
import com.vineet.cc.exceptions.InvalidInputException;

/**
 * Declared public API interface for card validation.
 *
 */
public interface CardNumberValidator {
	
	public void validate(String cardNumber) throws InvalidInputException, InvalidCardException;
}
