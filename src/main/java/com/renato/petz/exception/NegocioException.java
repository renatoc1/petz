package com.renato.petz.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class NegocioException extends RuntimeException {

	private HttpStatus httpStatus;

	private Object obj;

	public NegocioException(String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public NegocioException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
	
	public NegocioException(String msg, Throwable ex, HttpStatus status) {
		super(msg, ex);
		this.httpStatus = status;
	}

	public NegocioException(Object o, String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.obj = o;
	}

	public NegocioException(Object o, String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
		this.obj = o;
	}

	public NegocioException(Object o, String msg, Throwable ex, HttpStatus status) {
		super(msg, ex);
		this.httpStatus = status;
		this.obj = o;
	}
	
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}

}
