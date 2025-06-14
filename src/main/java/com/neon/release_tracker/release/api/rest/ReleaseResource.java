package com.neon.release_tracker.release.api.rest;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.api.rest.dto.ReleaseFilterRequestDto;
import com.neon.release_tracker.release.domain.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releases")
@RequiredArgsConstructor
public class ReleaseResource {
  private final ReleaseService releaseService;

  @GetMapping("/statuses")
  @Operation(summary = "Get release statuses", description = "Returns a list of possible release statuses")
  public ResponseEntity<List<String>> getReleaseStatuses() {
    List<String> releaseStatuses = releaseService.getReleaseStatuses();
    return ResponseEntity.ok().body(releaseStatuses);
  }

  @GetMapping("")
  @Operation(summary = "Get filtered releases", description = "Returns a filtered and paginated list of releases")
  public ResponseEntity<List<ReleaseDto>> getFiltered(@ModelAttribute ReleaseFilterRequestDto filter, Pageable pageable) {
    List<ReleaseDto> releases = releaseService.findFiltered(filter, pageable);
    return ResponseEntity.ok().body(releases);
  }

  @GetMapping("/{id:\\d+}")
  @Operation(summary = "Get release by id", description = "Returns one release by id")
  public ResponseEntity<ReleaseDto> getRelease(@PathVariable("id") Long id) {
    ReleaseDto foundRelease = releaseService.getOne(id);
    return ResponseEntity.ok().body(foundRelease);
  }

  @PostMapping("")
  @Operation(summary = "Create release", description = "Persist release in the DB")
  public ResponseEntity<ReleaseDto> createRelease(@Valid @RequestBody ReleaseDto releaseDto) {
    ReleaseDto createdRelease = releaseService.createRelease(releaseDto);
    return ResponseEntity.ok().body(createdRelease);
  }

  @PatchMapping("/{id:\\d+}")
  @Operation(summary = "Partial update", description = "Updates only the fields provided in the request body.\n" +
      "Any fields omitted from the request will remain unchanged in the existing resource.")
  public ResponseEntity<ReleaseDto> partialUpdateRelease(@PathVariable("id") Long id, @RequestBody ReleaseDto releaseDto) {
    ReleaseDto updatedRelease = releaseService.partialUpdateRelease(id, releaseDto);
    return ResponseEntity.ok().body(updatedRelease);
  }

  @PutMapping("/{id:\\d+}")
  @Operation(summary = "Full update", description = "Replaces the entire resource.\n" +
      "All fields must be provided in the request body. Missing fields may be set to null or cause validation errors.")
  public ResponseEntity<ReleaseDto> fullUpdateRelease(@PathVariable("id") Long id, @Valid @RequestBody ReleaseDto releaseDto) {
    ReleaseDto updatedRelease = releaseService.fullUpdateRelease(id, releaseDto);
    return ResponseEntity.ok().body(updatedRelease);
  }

  @DeleteMapping("/{id:\\d+}")
  @Operation(summary = "Delete a release by id", description = "Soft-deletes the resource.\n" +
      "Marks the resource as inactive without physically removing it from the database. This allows the record to be retained for audit or recovery purposes.")
  public ResponseEntity<ReleaseDto> deleteRelease(@PathVariable("id") Long id) {
    releaseService.deleteRelease(id);
    return ResponseEntity.ok().build();
  }
}
