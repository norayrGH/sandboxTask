package com.sandboxtask.task.controller;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.response.SandboxKidResponse;
import com.sandboxtask.task.dto.response.SandboxUserResponse;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import com.sandboxtask.task.mapper.SanBoxMapper;
import com.sandboxtask.task.service.kid.SandboxUserKidsService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}")
public class SandBoxKidController {

  private final SandboxUserKidsService sandboxUserKidsService;

  @GetMapping(value = "/kids")
  public Page<SandboxKidResponse> getAllKids(@PathVariable(name = "userId") Long userId, Pageable pageable) {
    log.info("Handling request for getting all SandBoxKids with page size {}, with parent id {} and page number {}. ",
        pageable.getPageSize(), userId, pageable.getPageNumber());
    Page<UserKid> all = sandboxUserKidsService.findAll(userId, pageable);
    List<SandboxKidResponse> collect = all.stream()
        .map(SanBoxMapper::mapToResponse)
        .collect(Collectors.toList());
    return new PageImpl<>(collect, pageable, all.getTotalElements());
  }

  @PostMapping(value = "/kids")
  public ResponseEntity<SandboxKidResponse> createKid(@PathVariable(name = "userId") Long userId,
      @RequestBody SandboxUserKidsCommand kidsCommand) {
    log.info("Handling request for creating kid with this command {}", kidsCommand);
    UserKid sandboxUserKid = sandboxUserKidsService.createSandboxUserKid(userId, kidsCommand);
    SandboxKidResponse response = SanBoxMapper.mapToResponse(sandboxUserKid);
    return ResponseEntity.ok(response);
  }


}
