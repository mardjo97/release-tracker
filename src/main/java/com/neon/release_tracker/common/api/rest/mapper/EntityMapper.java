package com.neon.release_tracker.common.api.rest.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<D, E> {
  E toEntity(D dto);

  D toDto(E entity);

  List<E> toEntity(List<D> dtoList);

  List<D> toDto(List<E> entityList);

  void fullUpdate(@MappingTarget E entity, D dto);

  void partialUpdate(@MappingTarget E entity, D dto);
}
