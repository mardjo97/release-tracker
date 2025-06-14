package com.neon.release_tracker.release.domain.service;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.api.rest.dto.ReleaseFilterRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface ReleaseService {
  List<String> getReleaseStatuses();

  List<ReleaseDto> findFiltered(ReleaseFilterRequestDto filter, Pageable pageable);

  ReleaseDto getOne(Long id);

  ReleaseDto createRelease(ReleaseDto releaseDto);

  ReleaseDto partialUpdateRelease(Long id, ReleaseDto releaseDto);

  ReleaseDto fullUpdateRelease(Long id, ReleaseDto releaseDto);

  void deleteRelease(Long id);
}
