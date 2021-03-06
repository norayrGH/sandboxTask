package com.sandboxtask.task.endtoend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import com.sandboxtask.task.dto.create.SandboxUserCreationCommand;
import com.sandboxtask.task.dto.response.SandboxUserResponse;
import com.sandboxtask.task.dto.update.SandboxUserUpdateCommand;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import com.sandboxtask.task.repository.UserKidRepository;
import com.sandboxtask.task.repository.UserRepository;
import java.util.List;
import java.util.stream.IntStream;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

class UserControllerTest extends BaseTestConfig {

  private final String LOCAL_HOST = "http://localhost:";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserKidRepository userKidRepository;


  @BeforeEach
  void cleanUp() {
    userKidRepository.deleteAll();
    userRepository.deleteAll();
  }

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
    List<SandboxUserResponse> cars1 = mapper.readValue(users, new TypeReference<>() {
    });
    cars1.forEach(echUser -> {
      Assertions.assertEquals(echUser.getEmailAddress(), user.getEmailAddress());
      Assertions.assertEquals(echUser.getFirstName(), user.getFirstName());
    });
  }


  @Test
  void createUser() {
    EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters());
    SandboxUserCreationCommand creationCommand = easyRandom.nextObject(SandboxUserCreationCommand.class);

    SandboxUserResponse response = this.restTemplate.postForObject(LOCAL_HOST + port + "/users?page=0&size=10",
        creationCommand, SandboxUserResponse.class);

    Assertions.assertEquals(response.getEmailAddress(), creationCommand.getEmailAddress());
    Assertions.assertEquals(response.getFirstName(), creationCommand.getFirstName());
    Assertions.assertEquals(response.getLastName(), creationCommand.getLastName());
    Assertions.assertNotNull(response.getId());
    List<UserKid> kidsByUserId = userKidRepository.findByUserId(response.getId());
    List<SandboxUserKidsCommand> kidsToCreate = creationCommand.getKids();
    IntStream
        .range(0, kidsByUserId.size())
        .boxed()
        .forEach(index -> {
          Assertions.assertEquals(kidsByUserId.get(index).getAge(), kidsToCreate.get(index).getAge());
          Assertions.assertEquals(kidsByUserId.get(index).getFirstName(), kidsToCreate.get(index).getFirstName());
          Assertions.assertEquals(kidsByUserId.get(index).getUser().getId(), response.getId());
        });
  }


  @Test
  void updateUser() {
    var user = new User();
    user.setEmailAddress("test");
    user.setFirstName("test");
    user.setLastName("test");
    User saveUser = userRepository.save(user);
    EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters());
    HttpEntity<SandboxUserUpdateCommand> updateCommand = new HttpEntity<>(
        easyRandom.nextObject(SandboxUserUpdateCommand.class));
    ResponseEntity<SandboxUserUpdateCommand> response = this.restTemplate.exchange(
        LOCAL_HOST + port + "/users/" + saveUser.getId(),
        HttpMethod.PUT, updateCommand, SandboxUserUpdateCommand.class);

    Assertions.assertEquals(response.getBody().getEmailAddress(), updateCommand.getBody().getEmailAddress());
    Assertions.assertEquals(response.getBody().getFirstName(), updateCommand.getBody().getFirstName());
    Assertions.assertEquals(response.getBody().getLastName(), updateCommand.getBody().getLastName());
    Assertions.assertNotNull(response.getBody().getId());
  }

  @Test
  void deleteUser() {
    var user = new User();
    user.setEmailAddress("test");
    user.setFirstName("test");
    user.setLastName("test");
    var userKid = new UserKid();
    User saveUser = userRepository.save(user);
    userKid.setUser(saveUser);
    userKid.setFirstName("Valodik");
    userKid.setAge(16);
    userKidRepository.save(userKid);

    this.restTemplate.delete(LOCAL_HOST + port + "/users/{userId}", saveUser.getId());
    Assertions.assertTrue(userKidRepository.findByUserId(saveUser.getId()).isEmpty());
    Assertions.assertTrue(userRepository.findUserById(saveUser.getId()).isEmpty());

  }

}
