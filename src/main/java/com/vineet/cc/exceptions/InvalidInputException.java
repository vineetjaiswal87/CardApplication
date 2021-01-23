/**
 * 
 */
package com.vineet.cc.exceptions;

/**
 * Exception to be thrown for invalid data input.
 *
 */
public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = 7547200014335275877L;

	public InvalidInputException(String message) {
		super(message);
	}
}
