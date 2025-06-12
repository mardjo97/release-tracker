package com.neon.release_tracker.release.api.rest.mapper;

import com.neon.release_tracker.common.api.rest.mapper.EntityMapper;
import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReleaseMapper extends EntityMapper<ReleaseDto, ReleaseEntity> {
  @Named("releaseToDto")
  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "releaseDate", target = "releaseDate")
  @Mapping(source = "status", target = "status")
  ReleaseDto toDto(ReleaseEntity s);

  @Named("releaseToDtos")
  default List<ReleaseDto> toDto(List<ReleaseEntity> s) {
    return s.stream()
        .map(this::toDto)
        .toList();
  }
}
