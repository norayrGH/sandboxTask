package com.sandboxtask.task.repository;

import com.sandboxtask.task.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserById(Long id);
}
