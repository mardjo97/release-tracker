package com.neon.release_tracker.release.api.rest;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.api.rest.dto.ReleaseFilterRequest;
import com.neon.release_tracker.release.domain.service.ReleaseService;
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

  @GetMapping("")
  public ResponseEntity<List<ReleaseDto>> getFiltered(@ModelAttribute ReleaseFilterRequest filter, Pageable pageable) {
    List<ReleaseDto> releases = releaseService.findFiltered(filter, pageable);
    return ResponseEntity.ok().body(releases);
  }

  @GetMapping("/{id:\\d+}")
  public ResponseEntity<ReleaseDto> getFiltered(@PathVariable("id") Long id) {
    ReleaseDto foundRelease = releaseService.getOne(id);
    return ResponseEntity.ok().body(foundRelease);
  }

  @PostMapping("")
  public ResponseEntity<ReleaseDto> createRelease(@Valid @RequestBody ReleaseDto releaseDto) {
    ReleaseDto createdRelease = releaseService.createRelease(releaseDto);
    return ResponseEntity.ok().body(createdRelease);
  }

  @PatchMapping("/{id:\\d+}")
  public ResponseEntity<ReleaseDto> partialUpdateRelease(@PathVariable("id") Long id, @Valid @RequestBody ReleaseDto releaseDto) {
    ReleaseDto updatedRelease = releaseService.partialUpdateRelease(id, releaseDto);
    return ResponseEntity.ok().body(updatedRelease);
  }

  @PutMapping("/{id:\\d+}")
  public ResponseEntity<ReleaseDto> fullUpdateRelease(@PathVariable("id") Long id, @Valid @RequestBody ReleaseDto releaseDto) {
    ReleaseDto updatedRelease = releaseService.fullUpdateRelease(id, releaseDto);
    return ResponseEntity.ok().body(updatedRelease);
  }

  @DeleteMapping("/{id:\\d+}")
  public ResponseEntity<ReleaseDto> deleteRelease(@PathVariable("id") Long id) {
    releaseService.deleteRelease(id);
    return ResponseEntity.ok().build();
  }
}
