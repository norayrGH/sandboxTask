package com.sandboxtask.task.service.kid;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import com.sandboxtask.task.entity.UserKid;
import com.sandboxtask.task.repository.UserKidRepository;
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


  @Override
  public Page<UserKid> findAll(Long userId, Pageable pageable) {
    return userKidRepository.findByUserId(userId, pageable);
  }

  @Override
  public UserKid createSandboxUserKid(Long userId, SandboxUserKidsCommand createCommand) {
    return null;
  }

  @Override
  public UserKid updateSandboxUserKid(Long userId, Long kidId, SandboxUserKidsCommand userUpdateCommand) {
    return null;
  }

  @Override
  public void deleteSandboxUserKid(Long userId, Long kidId) {

  }
}
