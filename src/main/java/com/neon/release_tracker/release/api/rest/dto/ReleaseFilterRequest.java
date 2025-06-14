package com.neon.release_tracker.release.api.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReleaseFilterRequest {
  private String name;
  private String status;
  private String description;
  private LocalDateTime releaseDateFrom;
  private LocalDateTime releaseDateTo;
}