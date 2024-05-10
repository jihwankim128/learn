package org.delivery.api.exception;

import java.util.List;

import org.delivery.api.exception.model.ErrorResponse;
import org.delivery.api.exception.model.ExceptionResponse;
import org.delivery.api.exception.model.NotFoundException;
import org.delivery.api.exception.model.info.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> exceptionHandler(final Exception e) {
		e.printStackTrace();
		log.error("[" + e.getClass() + "] : " + e.getMessage());
		return ResponseEntity.internalServerError()
			.body(ExceptionResponse.from(ExceptionType.INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(
		final MethodArgumentNotValidException e
	) {
		final List<ErrorResponse> errorResponses = e.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(fieldError -> new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
			.toList();
		log.warn("[" + e.getClass() + "] " + errorResponses);

		return ResponseEntity.badRequest()
			.body(ExceptionResponse.from(ExceptionType.INVALID_VALUE_ERROR));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> constraintViolationException(final ConstraintViolationException e) {
		final List<ErrorResponse> errorResponses = e.getConstraintViolations()
			.stream()
			.map(error -> new ErrorResponse(error.getPropertyPath().toString(), error.getMessage()))
			.toList();
		log.warn("[" + e.getClass() + "] " + errorResponses);

		return ResponseEntity.badRequest()
			.body(ExceptionResponse.from(ExceptionType.DATABASE_CONSTRAINTS_ERROR));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
		final MethodArgumentTypeMismatchException e
	) {
		final ErrorResponse errorResponse = new ErrorResponse(
			e.getName(),
			e.getRequiredType().getSimpleName() + " 타입으로 변환할 수 없는 요청입니다."
		);
		log.warn("[" + e.getClass() + "] " + errorResponse);
		return ResponseEntity.badRequest()
			.body(ExceptionResponse.from(ExceptionType.INVALID_VALUE_ERROR));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(
		final MissingServletRequestParameterException e
	) {
		final ErrorResponse errorResponse = new ErrorResponse(
			e.getParameterName(),
			"파라미터가 필요합니다."
		);
		log.warn("[" + e.getClass() + "] " + errorResponse);
		return ResponseEntity.badRequest()
			.body(ExceptionResponse.from(ExceptionType.INVALID_PARAMETER_ERROR));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleBadRequestException(final BadRequestException e) {
		log.warn("[" + e.getClass() + "] : " + e.getMessage());
		return ResponseEntity.badRequest()
			.body(ExceptionResponse.from(e.getExceptionType()));
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleNotFoundException(final NotFoundException e) {
		log.warn("[" + e.getClass() + "] : " + e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(ExceptionResponse.from(e.getExceptionType()));
	}

}
