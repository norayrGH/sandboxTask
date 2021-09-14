package com.sandboxtask.task.service.user;

import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.update.SandboxUserUpdateCommand;
import com.sandboxtask.task.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SandboxUserService {

  Page<User> findAll(Pageable pageable);

  User createSandboxUser(SandboxUserCreationCommand createCommand);

  User updateSandboxUser(Long userId, SandboxUserUpdateCommand userUpdateCommand);

  void deleteSandboxUser(Long userId);
}
