package com.neon.release_tracker.release.domain.service;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface ReleaseService {
  List<ReleaseDto> findFiltered(Pageable pageable);
  ReleaseDto getOne(Long id);
  ReleaseDto createRelease(ReleaseDto releaseDto);
  ReleaseDto partialUpdateRelease(Long id, ReleaseDto releaseDto);
  ReleaseDto fullUpdateRelease(Long id, ReleaseDto releaseDto);
  void deleteRelease(Long id);
}
