package com.sandboxtask.task.repository;

import com.sandboxtask.task.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Page<User> findByUserId(Long postId, Pageable pageable);
  Optional<User> findByUuid(UUID uuid);
  Optional<User> findUserById(Long id);
}
