package com.neon.release_tracker.release.api.rest.dto;

import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReleaseDto {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime releaseDate;
  private ReleaseStatusEnum status;
}
