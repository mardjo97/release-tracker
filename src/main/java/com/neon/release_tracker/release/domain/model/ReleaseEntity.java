package com.neon.release_tracker.release.domain.model;

import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "`release`")
@Getter
@Setter
public class ReleaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "release_date", nullable = false)
  private LocalDateTime releaseDate;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "last_update_at", nullable = false)
  private LocalDateTime lastUpdateAt;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ReleaseStatusEnum status;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled = true;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.lastUpdateAt = this.createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
    this.lastUpdateAt = LocalDateTime.now();
  }
}
