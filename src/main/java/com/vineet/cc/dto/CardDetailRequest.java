package com.vineet.cc.dto;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * Pojo to capture the user request to add card details
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
	
	public String getMaskedCardNumber() {
		return StringUtils.overlay(cardNumber, "******", 4, 15);
	}

	@Override
	public String toString() {
		return "CardDetailRequest [name=" + name + ", cardNumber=" + getMaskedCardNumber() + ", limitAmt=" + limitAmt + ", userId="
				+ userId + "]";
	}
}
