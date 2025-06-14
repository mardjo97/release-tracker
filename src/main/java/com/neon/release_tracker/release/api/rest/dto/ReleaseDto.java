package com.neon.release_tracker.release.api.rest.dto;

import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReleaseDto {
  private Long id;
  @NotNull(message = "Name must not be null")
  private String name;
  @NotNull(message = "Description must not be null")
  private String description;
  @NotNull(message = "Release date must not be null")
  private LocalDateTime releaseDate;
  private LocalDateTime createdAt;
  private LocalDateTime lastUpdateAt;
  @NotNull(message = "Status must not be null")
  private ReleaseStatusEnum status;
}
