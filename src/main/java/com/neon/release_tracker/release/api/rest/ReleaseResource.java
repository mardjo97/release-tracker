package com.neon.release_tracker.release.api.rest;

import com.neon.release_tracker.release.api.rest.dto.ReleaseDto;
import com.neon.release_tracker.release.domain.service.ReleaseService;
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
  public ResponseEntity<List<ReleaseDto>> getFiltered(Pageable pageable) {
    List<ReleaseDto> releases = releaseService.findFiltered(pageable);
    return ResponseEntity.ok().body(releases);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReleaseDto> getFiltered(@PathVariable("id") Long id) {
    ReleaseDto foundRelease = releaseService.getOne(id);
    return ResponseEntity.ok().body(foundRelease);
  }

  @PostMapping("")
  public ResponseEntity<ReleaseDto> createRelease(@RequestBody ReleaseDto ReleaseDto) {
    ReleaseDto createdEntity = releaseService.createRelease(ReleaseDto);
    return ResponseEntity.ok().body(createdEntity);
  }
}
