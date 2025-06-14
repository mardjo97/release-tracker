package com.neon.release_tracker.release.api.rest.mapper;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReleaseMapperImplTest {

  @InjectMocks
  private ReleaseMapperImpl releaseMapper;

  @Test
  void shouldMapReleaseToEntitySuccessfully() {
    ReleaseDto dto = new ReleaseDto();
    dto.setName("Test Release");
    dto.setDescription("Test Description");
    dto.setReleaseDate(LocalDateTime.now());
    dto.setStatus(ReleaseStatusEnum.CREATED);

    ReleaseEntity result = releaseMapper.toEntity(dto);

    assertNotNull(result);
    assertEquals(dto.getName(), result.getName());
    assertEquals(dto.getDescription(), result.getDescription());
    assertEquals(dto.getReleaseDate(), result.getReleaseDate());
    assertEquals(dto.getStatus(), result.getStatus());
    assertNull(dto.getCreatedAt());
    assertNull(dto.getLastUpdateAt());
  }
}
