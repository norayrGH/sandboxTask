package com.sandboxtask.task.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SandboxUserResponse {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private UUID uuid;

}
