package com.neon.release_tracker.release.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.domain.enums.ReleaseStatusEnum;
import com.neon.release_tracker.release.domain.model.ReleaseEntity;
import com.neon.release_tracker.release.infrastructure.persistence.ReleaseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReleaseResourceIT {
  @Autowired
  private ReleaseRepository releaseRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  ReleaseEntity releaseEntity;

  @BeforeEach
  void prepareData() {
    ReleaseEntity release = new ReleaseEntity();
    release.setName("Existing Release");
    release.setDescription("Existing release description");
    release.setReleaseDate(LocalDateTime.now().plusDays(1));
    release.setStatus(ReleaseStatusEnum.DONE);
    release.setEnabled(true);

    releaseEntity = releaseRepository.save(release);
  }

  @AfterEach
  void cleanup() {
    releaseRepository.deleteAll();
  }

  @Test
  void shouldGetReleaseByIdSuccessfully() throws Exception {
    mockMvc.perform(get("/api/releases/{id}", releaseEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(releaseEntity.getId()))
        .andExpect(jsonPath("$.name").value(releaseEntity.getName()))
        .andExpect(jsonPath("$.description").value(releaseEntity.getDescription()))
        .andExpect(jsonPath("$.releaseDate").isNotEmpty())
        .andExpect(jsonPath("$.createdAt").isNotEmpty())
        .andExpect(jsonPath("$.lastUpdateAt").isNotEmpty())
        .andExpect(jsonPath("$.status").value(releaseEntity.getStatus().name()));
  }

  @Test
  void shouldCreateReleaseSuccessfully() throws Exception {
    ReleaseDto dto = new ReleaseDto();
    dto.setName("Test Release");
    dto.setDescription("Test Release Description");
    dto.setReleaseDate(LocalDateTime.now().plusDays(1));
    dto.setStatus(ReleaseStatusEnum.DONE);

    mockMvc.perform(post("/api/releases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Test Release"));
  }

  @Test
  void shouldUpdateReleaseSuccessfully() throws Exception {
    ReleaseDto updatedDto = new ReleaseDto();
    updatedDto.setName("Updated Release Name");
    updatedDto.setDescription("Updated Description");
    updatedDto.setReleaseDate(LocalDateTime.now().plusDays(2));
    updatedDto.setStatus(ReleaseStatusEnum.QA_DONE_ON_STAGING);

    mockMvc.perform(put("/api/releases/{id}", releaseEntity.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Updated Release Name"))
        .andExpect(jsonPath("$.description").value("Updated Description"))
        .andExpect(jsonPath("$.releaseDate").isNotEmpty())
        .andExpect(jsonPath("$.createdAt").isNotEmpty())
        .andExpect(jsonPath("$.lastUpdateAt").isNotEmpty())
        .andExpect(jsonPath("$.status").value(ReleaseStatusEnum.QA_DONE_ON_STAGING.name()));
  }

  @Test
  @Transactional
  void shouldDeleteReleaseSuccessfully() throws Exception {
    mockMvc.perform(delete("/api/releases/{id}", releaseEntity.getId()))
        .andExpect(status().isOk());

    ReleaseEntity checkReleaseEntity = releaseRepository.getReferenceById(releaseEntity.getId());
    assertFalse(checkReleaseEntity.getEnabled());
  }
}
