package com.sandboxtask.task.repository;

import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKidRepository extends JpaRepository<UserKid, Long> {

  Page<UserKid> findByUserId(Long userId, Pageable pageable);
  List<UserKid> findByUserId(Long userId);
  Optional<UserKid> findByIdAndUserId(Long id, Long userId);
}
