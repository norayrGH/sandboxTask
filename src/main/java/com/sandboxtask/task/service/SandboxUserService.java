package com.sandboxtask.task.service;

import com.sandboxtask.task.dto.SandboxUserCreationCommand;
import com.sandboxtask.task.entity.User;

public interface SandboxUserService {
  User createSandboxUser(SandboxUserCreationCommand createCommand);
}
