package com.neon.release_tracker.release.api.rest.mapper;

import com.neon.release_tracker.common.api.rest.mapper.EntityMapper;
import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReleaseMapper extends EntityMapper<ReleaseDto, ReleaseEntity> {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "lastUpdateAt", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  void partialUpdate(@MappingTarget ReleaseEntity entity, ReleaseDto dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "lastUpdateAt", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  void fullUpdate(@MappingTarget ReleaseEntity entity, ReleaseDto dto);

  default void softDelete(@MappingTarget ReleaseEntity entity) {
    entity.setEnabled(false);
  }
}
