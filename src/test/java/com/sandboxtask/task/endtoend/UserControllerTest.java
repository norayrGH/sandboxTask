package com.sandboxtask.task.endtoend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandboxtask.task.dto.response.SandboxUserResponse;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.repository.UserRepository;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import com.fasterxml.jackson.core.type.TypeReference;

class UserControllerTest extends BaseTestConfig {

  private final String LOCAL_HOST = "http://localhost:";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;


  @Test
  void getUsers() throws Exception {

    var user = new User();
    user.setEmailAddress("test");
    user.setFirstName("test");
    user.setLastName("test");
    userRepository.save(user);

    String json = this.restTemplate.getForObject(LOCAL_HOST + port + "/users?page=0&size=10", String.class);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(json);
    String users = jsonNode.get("content").toString();
    List<SandboxUserResponse> cars1 = mapper.readValue(users, new TypeReference<>() {});
    cars1.forEach(echUser -> {
      Assertions.assertEquals(echUser.getEmailAddress(), user.getEmailAddress());
      Assertions.assertEquals(echUser.getFirstName(), user.getFirstName());
      Assertions.assertEquals(echUser.getUuid(), user.getUuid());
    });
  }


}
