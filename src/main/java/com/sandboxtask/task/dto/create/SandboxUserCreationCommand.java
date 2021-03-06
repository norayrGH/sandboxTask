package com.sandboxtask.task.dto.create;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SandboxUserCreationCommand {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private List<SandboxUserKidsCommand> kids;

}
