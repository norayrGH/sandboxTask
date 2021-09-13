package com.sandboxtask.task.dto;

import java.util.List;
import lombok.Data;

@Data
public class SandboxUserCreationCommand {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private List<SandboxUserKidsCommand> kids;

}
