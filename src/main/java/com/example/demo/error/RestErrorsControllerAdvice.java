package com.example.demo.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class RestErrorsControllerAdvice implements ErrorController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    //TODO: create more other ExceptionHandlers

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Error: not found!")
	public void handleException(NotFoundException ex){
		log.debug("Handling NotFoundException: "+ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error: bad request!")
	public void handleException(IllegalArgumentException ex){
		log.debug("Handling IllegalArgumentException: "+ex.getMessage());
	}


	@Override
	public String getErrorPath() {
		return "/error";
	}

}
