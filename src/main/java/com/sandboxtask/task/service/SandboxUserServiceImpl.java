package com.sandboxtask.task.service;

import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.update.SandboxUserUpdateCommand;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.repository.UserRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @Override
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public User update(Long userId, SandboxUserUpdateCommand userUpdateCommand) {
    Optional<User> userById = userRepository.findUserById(userId);
    if (userById.isPresent()) {
      User user = userById.get();
      user.setEmailAddress(userUpdateCommand.getEmailAddress());
      user.setFirstName(userUpdateCommand.getFirstName());
      user.setLastName(userUpdateCommand.getLastName());
      return userRepository.save(user);
    } else {
      throw new EntityNotFoundException("Can not find entity with this Id");
    }
  }

  @Override
  public void deleteUser(Long userId) {
    Optional<User> userById = userRepository.findUserById(userId);

    if (userById.isPresent()) {
      userRepository.delete(userById.get());
    } else {
      throw new EntityNotFoundException("Can not find entity with this Id");
    }
  }
}
