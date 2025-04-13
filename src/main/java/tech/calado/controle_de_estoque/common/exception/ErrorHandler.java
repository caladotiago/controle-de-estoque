package tech.calado.controle_de_estoque.common.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, Error.class })
	public HttpEntity<Message> handlerException(Exception ex) {
		Message message = new Message("internal server error");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	public HttpEntity<Object> handlerResourceConflictException(ConflictException ex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(new Message(ex.getMessage()), responseHeaders, HttpStatus.CONFLICT);
	}

}
