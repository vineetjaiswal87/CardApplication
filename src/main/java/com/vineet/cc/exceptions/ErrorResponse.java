/**
 * 
 */
package com.vineet.cc.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * It is used to maintain a standard error response for the application.
 *
 */
@Getter
public class ErrorResponse {
	
	private final HttpStatus status;
	private final String message;
	private final Date timestamp;
	
	protected ErrorResponse(final String message, final HttpStatus status) {
		this.message = message;
		this.status = status;
		this.timestamp = new Date();
	}

	public static ErrorResponse of(final String message, final HttpStatus status) {
		return new ErrorResponse(message, status);
	}
}
