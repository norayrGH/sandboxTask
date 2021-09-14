package com.sandboxtask.task.service.kid;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import com.sandboxtask.task.entity.UserKid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SandboxUserKidsService {
  Page<UserKid> findAll(Long userId, Pageable pageable);

  UserKid createSandboxUserKid(Long userId, SandboxUserKidsCommand createCommand);

  UserKid updateSandboxUserKid(Long userId, Long kidId, SandboxUserKidsCommand userUpdateCommand);

  void deleteSandboxUserKid(Long userId, Long kidId);
}
