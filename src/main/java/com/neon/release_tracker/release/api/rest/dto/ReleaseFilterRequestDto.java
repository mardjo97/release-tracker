package com.neon.release_tracker.release.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReleaseFilterRequestDto {
  @Schema(description = "Filter releases by name part", example = "1.0")
  private String name;
  @Schema(description = "Filter releases by status", example = "DONE")
  private String status;
  @Schema(description = "Filter releases by description part", example = "Initial")
  private String description;
  @Schema(description = "Filter releases by release date after", example = "2025-06-01T12:00:00")
  private LocalDateTime releaseDateFrom;
  @Schema(description = "Filter releases by release date before", example = "2025-07-01T12:00:00")
  private LocalDateTime releaseDateTo;
}