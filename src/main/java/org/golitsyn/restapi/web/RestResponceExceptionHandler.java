package org.golitsyn.restapi.web;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for REST API
 *
 * Created by Sergey Golitsyn (deft) on 16.06.2019
 */

@ControllerAdvice
@NoArgsConstructor
public class RestResponceExceptionHandler extends ResponseEntityExceptionHandler {


  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

    return super
        .handleExceptionInternal(ex, message(status, ex), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return super
        .handleExceptionInternal(ex, message(status, ex), headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({DataIntegrityViolationException.class /*TODO add My custom exceptions **/})
  public final ResponseEntity<Object> handleBadRequest(final RuntimeException ex,
      final WebRequest request) {
    return super
        .handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }

  private ApiError message(final HttpStatus status, final Exception ex) {
    final String message =
        ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
    final String devMessage = ExceptionUtils.getRootCauseMessage(ex);
    return new ApiError(status.value(), message, devMessage);
  }
}
