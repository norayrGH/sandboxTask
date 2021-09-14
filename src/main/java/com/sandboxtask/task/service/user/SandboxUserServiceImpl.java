package com.sandboxtask.task.service.user;

import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.update.SandboxUserUpdateCommand;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import com.sandboxtask.task.repository.UserKidRepository;
import com.sandboxtask.task.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SandboxUserServiceImpl implements SandboxUserService {

  private final UserRepository userRepository;
  private final UserKidRepository userKidRepository;

  @Override
  public User createSandboxUser(SandboxUserCreationCommand createCommand) {
    var user = new User();
    user.setEmailAddress(createCommand.getEmailAddress());
    user.setFirstName(createCommand.getFirstName());
    user.setLastName(createCommand.getLastName());
    User savedUser = userRepository.save(user);
    List<UserKid> newKids = createCommand.getKids().stream()
        .map(kid -> {
          var newKid = new UserKid();
          newKid.setFirstName(kid.getFirstName());
          newKid.setAge(kid.getAge());
          newKid.setUser(savedUser);
          return newKid;
        }).collect(Collectors.toList());
    userKidRepository.saveAll(newKids);
    return savedUser;
  }

  @Override
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public User updateSandboxUser(Long userId, SandboxUserUpdateCommand userUpdateCommand) {
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
  @Transactional
  public void deleteSandboxUser(Long userId) {
    Optional<User> userById = userRepository.findUserById(userId);
    if (userById.isPresent()) {
      userKidRepository.removeAllByUserId(userId);
      userRepository.delete(userById.get());
    } else {
      throw new EntityNotFoundException("Can not find entity with this Id");
    }
  }
}
