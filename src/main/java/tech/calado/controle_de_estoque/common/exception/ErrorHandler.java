package tech.calado.controle_de_estoque.common.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, Error.class })
	public HttpEntity<Message> handlerException(Exception ex) {
		Message message = Message.of("internal server error");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HttpEntity<Message> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ObjectError> objectErrors = bindingResult.getAllErrors();
		Message message = fieldErrors.isEmpty() ? processObjectErrors(objectErrors) : processFieldErrors(fieldErrors);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	public HttpEntity<Message> handlerResourceConflictException(ConflictException ex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(Message.of(ex.getMessage()), responseHeaders, HttpStatus.CONFLICT);
	}

	private Message processObjectErrors(List<ObjectError> errors) {
		List<String> messageErrors = errors.stream().map(ObjectError::getDefaultMessage).toList();
		return new Message("validation error", messageErrors);
	}

	private Message processFieldErrors(List<FieldError> errors) {
		List<String> messageErrors = errors.stream().map(fieldError -> {
			String message = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
			return fieldError.getField().concat(":").concat(message);
		}).toList();

		return new Message("validation error", messageErrors);
	}

}
