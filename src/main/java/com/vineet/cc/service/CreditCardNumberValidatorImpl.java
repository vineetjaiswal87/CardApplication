package com.vineet.cc.service;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vineet.cc.constants.ErrorMessage;
import com.vineet.cc.exceptions.InvalidCardException;
import com.vineet.cc.exceptions.InvalidInputException;

/**
 * Class implementing the CardNumberValidator for input validation.
 *
 */
@Service
public class CreditCardNumberValidatorImpl implements CardNumberValidator {
	Logger logger = LoggerFactory.getLogger(CreditCardService.class);
	
	public void validate(String cardNumber) throws InvalidInputException, InvalidCardException {
		if(!isValidInput(cardNumber)) {
			throw new InvalidInputException(ErrorMessage.INVALID_DATA);
		}
		
		if(!isValidCard(cardNumber)) {
			throw new InvalidCardException(ErrorMessage.INVALID_CARD);
		}
		
	}
	/** It validates the card number against the luhn's algorithm.
	 * @param cardNumber User provided card Number.
	 * @return
	 */
	public boolean isValidCard(String cardNumber) {
		int size = cardNumber.length();
        String[] digits = cardNumber.split("");
        int total = IntStream
            .range(0, size)
            .map(index -> index % 2 == size % 2 ? 2*Integer.valueOf(digits[index]) : Integer.valueOf(digits[index]))
            .map(doubled -> doubled > 9 ? doubled - 9 : doubled)
            .sum();
        logger.info("Total sum Value::" + total);
        return total % 10 == 0;
	}
	
	/** It validates the input length and that it is not alphanumeric. 
	 * @param number User provided card Number.
	 * @return
	 */
	public boolean isValidInput(String cardNumber) {
		return (StringUtils.hasLength(cardNumber) && cardNumber.length() <= 19 && containsAllNumbers(cardNumber));
		
	}
	
	/** It validates if the input only contains number. 
	 * @param number User provided card Number.
	 * @return
	 */
	public boolean containsAllNumbers(String number) {
		for (int i = 0; i < number.length(); i++) {
			int asciiVal = (int) number.charAt(i);
			if (asciiVal < 48 || asciiVal > 57) {
				return false;
			}
		}
		return true;
	}
}
