package com.neon.release_tracker.release.api.rest.dto;

import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReleaseDto {
  private Long id;
  @Schema(example = "Version 1.0")
  @NotNull(message = "Name must not be null")
  private String name;
  @Schema(example = "Initial release of the system")
  @NotNull(message = "Description must not be null")
  private String description;
  @Schema(example = "2025-07-01T12:00:00")
  @NotNull(message = "Release date must not be null")
  private LocalDateTime releaseDate;
  @Schema(example = "2025-06-01T12:00:00")
  private LocalDateTime createdAt;
  @Schema(example = "2025-06-03T13:00:00")
  private LocalDateTime lastUpdateAt;
  @Schema(example = "DONE")
  @NotNull(message = "Status must not be null")
  private ReleaseStatusEnum status;
}
