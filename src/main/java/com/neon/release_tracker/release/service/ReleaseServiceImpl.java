package com.neon.release_tracker.release.service;

import com.neon.release_tracker.common.domain.exception.RecordNotFoundException;
import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.api.rest.mapper.ReleaseMapper;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import com.neon.release_tracker.release.domain.service.ReleaseService;
import com.neon.release_tracker.release.infrastructure.persistence.ReleaseRepository;
import jakarta.persistence.EntityNotFoundException;
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
    return releaseMapper.toDto(getRelease(id));
  }

  public ReleaseDto createRelease(ReleaseDto releaseDto) {
    ReleaseEntity releaseEntity = releaseMapper.toEntity(releaseDto);
    releaseRepository.saveAndFlush(releaseEntity);
    return releaseMapper.toDto(releaseEntity);
  }

  public ReleaseDto partialUpdateRelease(Long id, ReleaseDto releaseDto) {
    ReleaseEntity existingRelease = getRelease(id);
    releaseMapper.partialUpdate(existingRelease, releaseDto);
    releaseRepository.saveAndFlush(existingRelease);
    return releaseMapper.toDto(existingRelease);
  }

  public ReleaseDto fullUpdateRelease(Long id, ReleaseDto releaseDto) {
    ReleaseEntity existingRelease = getRelease(id);
    releaseMapper.fullUpdate(existingRelease, releaseDto);
    releaseRepository.saveAndFlush(existingRelease);
    return releaseMapper.toDto(existingRelease);
  }

  public void deleteRelease(Long id) {
    ReleaseEntity existingRelease = getRelease(id);
    releaseMapper.softDelete(existingRelease);
    releaseRepository.saveAndFlush(existingRelease);
  }

  private ReleaseEntity getRelease(Long id) {
    try {
      ReleaseEntity existingEntity = releaseRepository.getReferenceById(id);
      if (Boolean.FALSE.equals(existingEntity.getEnabled())) {
        throw new RecordNotFoundException("Release with ID " + id + " not found.");
      }
      return existingEntity;
    } catch (EntityNotFoundException e) {
      throw new RecordNotFoundException("Release with ID " + id + " not found.");
    }
  }
}
