/**
 * 
 */
package com.vineet.cc.exceptions;

/**
 * Exception to be thrown for invalid card.
 *
 */
public class InvalidCardException extends Exception {
	
	private static final long serialVersionUID = -3116986563994630204L;

	public InvalidCardException(String message) {
		super(message);
	}
}
