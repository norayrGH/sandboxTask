package com.sandboxtask.task.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SandboxUserUpdateCommand {

  private Long id;
  private String firstName;
  private String lastName;
  private String emailAddress;

}
