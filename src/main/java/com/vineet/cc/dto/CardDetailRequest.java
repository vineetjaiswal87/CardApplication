package com.vineet.cc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * It is a pojo to capture user request to add card details
 *
 */
@Getter
@Setter
@Value
public class CardDetailRequest {
	private String name;
	private String cardNumber;
	private String limitAmt;
	private String userId;
	
}
