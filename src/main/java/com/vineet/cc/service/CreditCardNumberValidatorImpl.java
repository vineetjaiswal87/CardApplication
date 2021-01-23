package com.vineet.cc.service;

import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.vineet.cc.constants.ErrorMessage;
import com.vineet.cc.exceptions.InvalidCardException;
import com.vineet.cc.exceptions.InvalidInputException;

/**
 * Class implementing the CardNumberValidator for validating the incoming input request.
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
	/** This method validates the card number using the luhn's algorithm.
	 * @param cardNumber User provided card Number.
	 * @return true if valid card else false.
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
	
	/** This method validates the input length and that it is not alphanumeric. 
	 * @param number User provided card Number.
	 * @return true if valid input else false.
	 */
	public boolean isValidInput(String cardNumber) {
		return (StringUtils.isNotEmpty(cardNumber) && cardNumber.length() <= 19 && containsAllNumbers(cardNumber));
		
	}
	
	/** This method validates if the input only contains number. 
	 * @param number User provided card Number.
	 * @return true if the card number only has numbers else false.
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
