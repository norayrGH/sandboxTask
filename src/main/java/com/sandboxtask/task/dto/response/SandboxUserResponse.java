package com.sandboxtask.task.dto.response;

import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import java.util.List;
import lombok.Data;

@Data
public class SandboxUserResponse {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private List<SandboxUserKidsCommand> kids;

}
