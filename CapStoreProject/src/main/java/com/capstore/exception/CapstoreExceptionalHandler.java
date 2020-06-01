package com.capstore.exception;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CapstoreExceptionalHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public JSONObject noUserErrorMessage(UserNotFoundException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		return obj;
	}

	@ExceptionHandler(InvalidUserException.class)
	public JSONObject invalidUserErrorMessage(InvalidUserException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		return obj;
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public JSONObject incorrectPasswordErrorMessage(IncorrectPasswordException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		return obj;
	}
}
