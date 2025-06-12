package com.neon.release_tracker.release.service;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.api.rest.mapper.ReleaseMapper;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import com.neon.release_tracker.release.domain.service.ReleaseService;
import com.neon.release_tracker.release.infrastructure.persistence.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {
  private final ReleaseRepository releaseRepository;

  private final ReleaseMapper releaseMapper;

  public List<ReleaseDto> findFiltered(Pageable pageable) {
    Page<ReleaseEntity> page = releaseRepository.findByEnabledTrue(pageable);
    return releaseMapper.toDto(page.getContent());
  }

  public ReleaseDto getOne(Long id) {
    ReleaseEntity foundEntity = releaseRepository.getReferenceById(id);
    return releaseMapper.toDto(foundEntity);
  }

  public ReleaseDto createRelease(ReleaseDto releaseDto) {
    ReleaseEntity releaseEntity = releaseMapper.toEntity(releaseDto);
    releaseRepository.save(releaseEntity);
    return releaseMapper.toDto(releaseEntity);
  }
}
