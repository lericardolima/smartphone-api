package com.example.smartphone.api.exception.handler;

import java.time.LocalDateTime;

public class SmartphoneCustomError {

	private LocalDateTime timestamp;
	private String field;
	private String message;
	private String detailMessage;

	public SmartphoneCustomError(String field, String message, String detailMessage) {
		super();
		this.field = field;
		this.message = message;
		this.detailMessage = detailMessage;
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

}
