package com.neon.release_tracker.common.api.rest.mapper;

import com.neon.release_tracker.common.api.rest.dto.ErrorDto;
import com.neon.release_tracker.common.domain.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionMapper {

  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<ErrorDto> handleRecordNotFound(RecordNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleArgumentValidation(MethodArgumentNotValidException e) {
    List<String> errors = e.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage)
        .toList();
    return ResponseEntity
        .badRequest()
        .body(new ErrorDto("Invalid request content", HttpStatus.BAD_REQUEST.value(), errors));
  }
}
