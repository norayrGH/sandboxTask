package com.sandboxtask.task.service;

import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.update.SandboxUserUpdateCommand;
import com.sandboxtask.task.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SandboxUserService {

  User createSandboxUser(SandboxUserCreationCommand createCommand);

  Page<User> findAll(Pageable pageable);

  User update(Long userId, SandboxUserUpdateCommand userUpdateCommand);

  void deleteUser(Long userId);
}
