package com.sandboxtask.task.mapper;

import com.sandboxtask.task.dto.response.SandboxKidResponse;
import com.sandboxtask.task.dto.response.SandboxUserResponse;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SanBoxMapper {

  public static SandboxUserResponse mapToResponse(User sandboxUser) {
    return SandboxUserResponse.builder()
        .firstName(sandboxUser.getFirstName())
        .lastName(sandboxUser.getLastName())
        .emailAddress(sandboxUser.getEmailAddress())
        .id(sandboxUser.getId())
        .build();
  }

  public static SandboxKidResponse mapToResponse(UserKid sandboxKid) {
    return SandboxKidResponse.builder()
        .id(sandboxKid.getId())
        .firstName(sandboxKid.getFirstName())
        .age(sandboxKid.getAge())
        .parentInfo(mapToResponse(sandboxKid.getUser()))
        .build();
  }
}
