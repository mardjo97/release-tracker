package com.neon.release_tracker.release.service;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.api.rest.mapper.ReleaseMapper;
import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import com.neon.release_tracker.release.infrastructure.persistence.ReleaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReleaseServiceImplTest {
  @Mock
  private ReleaseMapper releaseMapper;

  @Mock
  private ReleaseRepository releaseRepository;

  @InjectMocks
  private ReleaseServiceImpl releaseService;

  @Test
  void shouldCreateReleaseSuccessfully() {
    ReleaseDto dto = new ReleaseDto();
    dto.setName("Test Release");
    dto.setDescription("Test Description");
    dto.setReleaseDate(LocalDateTime.now());
    dto.setStatus(ReleaseStatusEnum.CREATED);

    ReleaseEntity entity = new ReleaseEntity();
    entity.setName("Test Release");
    entity.setDescription("Test Description");
    entity.setReleaseDate(LocalDateTime.now());
    entity.setStatus(ReleaseStatusEnum.CREATED);

    when(releaseMapper.toEntity(dto)).thenReturn(entity);
    when(releaseRepository.saveAndFlush(entity)).thenReturn(entity);
    when(releaseMapper.toDto(entity)).thenReturn(dto);

    ReleaseDto result = releaseService.createRelease(dto);

    assertNotNull(result);
    verify(releaseMapper).toEntity(dto);
    verify(releaseRepository).saveAndFlush(entity);
    verify(releaseMapper).toDto(entity);
  }
}
