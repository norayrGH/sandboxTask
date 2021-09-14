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

  private Long id;
  private String lastName;
  private String firstName;
  private String emailAddress;

}
