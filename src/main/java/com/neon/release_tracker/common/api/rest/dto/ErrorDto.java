package com.neon.release_tracker.common.api.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDto {
  private String reason;
  private Integer statusCode;
  private LocalDateTime timestamp;
  private List<String> errors;

  public ErrorDto(String reason, Integer statusCode) {
    this.reason = reason;
    this.statusCode = statusCode;
    this.timestamp = LocalDateTime.now();
    this.errors = Collections.emptyList();
  }

  public ErrorDto(String reason, Integer statusCode, List<String> errors) {
    this.reason = reason;
    this.statusCode = statusCode;
    this.timestamp = LocalDateTime.now();
    this.errors = errors;
  }
}
