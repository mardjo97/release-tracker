package com.neon.release_tracker.release.infrastructure.persistence;

import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, Long> {

  Page<ReleaseEntity> findByEnabledTrue(Pageable pageable);
}
