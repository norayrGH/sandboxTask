package com.sandboxtask.task.dto.update;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import java.util.List;
import lombok.Data;

@Data
public class SandboxUserUpdateCommand {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private List<SandboxUserKidsCommand> kids;

}
