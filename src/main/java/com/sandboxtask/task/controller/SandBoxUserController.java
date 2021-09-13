package com.sandboxtask.task.controller;

import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.response.SandboxUserResponse;
import com.sandboxtask.task.dto.update.SandboxUserUpdateCommand;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.mapper.SanBoxMapper;
import com.sandboxtask.task.service.SandboxUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SandBoxUserController {

  private final SandboxUserService sandboxUserService;

  @GetMapping()
  public Page<User> getAllUsers(Pageable pageable) {
    return sandboxUserService.findAll(pageable);
  }

  @PostMapping()
  public ResponseEntity<SandboxUserResponse> createUser(@RequestBody SandboxUserCreationCommand creationCommand) {
    User sandboxUser = sandboxUserService.createSandboxUser(creationCommand);
    SandboxUserResponse response = SanBoxMapper.mapToResponse(sandboxUser);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<SandboxUserResponse> updateUser(@PathVariable Long userId,
      SandboxUserUpdateCommand userUpdateCommand) {
    User update = sandboxUserService.update(userId, userUpdateCommand);
    SandboxUserResponse response = SanBoxMapper.mapToResponse(update);
    return ResponseEntity.ok(response);
  }


  @DeleteMapping("/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
    sandboxUserService.deleteUser(userId);
    return ResponseEntity.ok().build();

  }


}
