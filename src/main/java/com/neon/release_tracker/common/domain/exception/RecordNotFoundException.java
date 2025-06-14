package com.neon.release_tracker.common.domain.exception;

public class RecordNotFoundException extends RuntimeException {
  public RecordNotFoundException(String message) {
    super(message);
  }
}
