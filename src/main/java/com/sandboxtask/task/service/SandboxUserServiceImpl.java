package com.sandboxtask.task.service;

import com.sandboxtask.task.dto.SandboxUserCreationCommand;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SandboxUserServiceImpl implements SandboxUserService {

  private final UserRepository userRepository;

  @Override
  public User createSandboxUser(SandboxUserCreationCommand createCommand) {
    log.info("Trying to create user from command");
    var user = new User();
    user.setEmailAddress(createCommand.getEmailAddress());
    user.setFirstName(createCommand.getFirstName());
    user.setLastName(createCommand.getLastName());

    return userRepository.save(user);
  }
}
