package com.sandboxtask.task.mapper;

import com.sandboxtask.task.dto.response.SandboxUserResponse;
import com.sandboxtask.task.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SanBoxMapper {

  public static SandboxUserResponse mapToResponse(User sandboxUser) {
    return SandboxUserResponse.builder()
        .firstName(sandboxUser.getFirstName())
        .lastName(sandboxUser.getLastName())
        .emailAddress(sandboxUser.getEmailAddress())
        .uuid(sandboxUser.getUuid())
        .build();
  }
}
