package com.sandboxtask.task.service.kid;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import com.sandboxtask.task.repository.UserKidRepository;
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
public class SandboxUserKidServiceImpl implements SandboxUserKidsService {

  private final UserKidRepository userKidRepository;
  private final UserRepository userRepository;

  @Override
  public Page<UserKid> findAll(Long userId, Pageable pageable) {
    return userKidRepository.findByUserId(userId, pageable);
  }

  @Override
  public UserKid createSandboxUserKid(Long userId, SandboxUserKidsCommand createCommand) {
    UserKid userKid = new UserKid();
    Optional<User> userById = userRepository.findUserById(userId);
    if (userById.isPresent()) {
      userKid.setUser(userById.get());
      userKid.setFirstName(createCommand.getFirstName());
      userKid.setAge(createCommand.getAge());

      return userKidRepository.save(userKid);
    } else {
      throw new EntityNotFoundException(String.format("can not find user with this id %s.", userId));
    }
  }

  @Override
  public UserKid updateSandboxUserKid(Long userId, Long kidId, SandboxUserKidsCommand userUpdateCommand) {
    return null;
  }

  @Override
  public void deleteSandboxUserKid(Long userId, Long kidId) {

  }
}
