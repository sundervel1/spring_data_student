package com.cg.student.exception;

public class StudentAlreadyExistsException extends RuntimeException {

	public StudentAlreadyExistsException(String message) {
		super(message);
	}

}
