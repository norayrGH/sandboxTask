package com.sandboxtask.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SandboxKidResponse {
  private Long id;
  private String firstName;
  private Integer age;
  private SandboxUserResponse parentInfo;
}
