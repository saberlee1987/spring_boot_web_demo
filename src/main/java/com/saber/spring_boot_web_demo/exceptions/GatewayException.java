package com.saber.spring_boot_web_demo.exceptions;

import com.saber.spring_boot_web_demo.dto.ErrorResponseDto;

public class GatewayException extends RuntimeException{
	private final int statusCode;
	private final String correlation;
	private final ErrorResponseDto errorResponse;

	public GatewayException(int statusCode, String correlation, ErrorResponseDto errorResponse) {
		this.statusCode = statusCode;
		this.correlation = correlation;
		this.errorResponse = errorResponse;
	}

	public GatewayException(String message, int statusCode, String correlation, ErrorResponseDto errorResponse) {
		super(message);
		this.statusCode = statusCode;
		this.correlation = correlation;
		this.errorResponse = errorResponse;
	}

	public GatewayException(String message, Throwable cause, int statusCode, String correlation, ErrorResponseDto errorResponse) {
		super(message, cause);
		this.statusCode = statusCode;
		this.correlation = correlation;
		this.errorResponse = errorResponse;
	}

	public GatewayException(Throwable cause, int statusCode, String correlation, ErrorResponseDto errorResponse) {
		super(cause);
		this.statusCode = statusCode;
		this.correlation = correlation;
		this.errorResponse = errorResponse;
	}

	public GatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int statusCode, String correlation, ErrorResponseDto errorResponse) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.statusCode = statusCode;
		this.correlation = correlation;
		this.errorResponse = errorResponse;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getCorrelation() {
		return correlation;
	}

	public ErrorResponseDto getErrorResponse() {
		return errorResponse;
	}
}
