/**
 * 
 */
package com.vineet.cc.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.vineet.cc.exceptions.InvalidCardException;
import com.vineet.cc.exceptions.InvalidInputException;

/**
 * Class to test Data Validation and Luhn's algorithm
 *
 */
public class CreditCardNumberValidatorImplTest {
	
	@Test
	void testValidCard() {
		CreditCardNumberValidatorImpl validator = new CreditCardNumberValidatorImpl();
		boolean isValid = validator.isValidCard("1111222233334444");
		Assertions.assertTrue(isValid);
	}
	
	@Test
	void testValidInput() {
		CreditCardNumberValidatorImpl validator = new CreditCardNumberValidatorImpl();
		boolean isValid = validator.isValidInput("1111222233335544");
		Assertions.assertTrue(isValid);
	}
	
	@Test
	void testInValidInput() {
		CreditCardNumberValidatorImpl validator = new CreditCardNumberValidatorImpl();
		InvalidInputException ex = assertThrows(InvalidInputException.class, ()->validator.validate("11112QQ22233"));
		assertEquals("Invalid input data", ex.getMessage());
	}
	
	@Test
	void testInValidCard() {
		CreditCardNumberValidatorImpl validator = new CreditCardNumberValidatorImpl();
		InvalidCardException ex = assertThrows(InvalidCardException.class, ()->validator.validate("1111222233"));
		assertEquals("Invalid card", ex.getMessage());
	}

}
