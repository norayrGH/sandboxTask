package com.sandboxtask.task.endtoend;

import static org.jeasy.random.FieldPredicates.named;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandboxtask.task.dto.SandboxUserKidsCommand;
import com.sandboxtask.task.dto.response.SandboxKidResponse;
import com.sandboxtask.task.entity.User;
import com.sandboxtask.task.entity.UserKid;
import com.sandboxtask.task.repository.UserKidRepository;
import com.sandboxtask.task.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

class KidControllerTest extends BaseTestConfig {

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
  void getKids() throws Exception {

    var user = new User();
    user.setEmailAddress("test");
    user.setFirstName("test");
    user.setLastName("test");
    User savedUser = userRepository.save(user);
    List<UserKid> userKids = new EasyRandom(new EasyRandomParameters()
        .randomize(named("id"), () -> null)
        .randomize(named("uuid"), UUID::randomUUID)
        .randomize(named("user"), () -> savedUser)
    ).objects(UserKid.class, 2).collect(Collectors.toList());
    userKidRepository.saveAll(userKids);

    String response = this.restTemplate.getForObject(LOCAL_HOST + port + "/users/{userId}/kids?page=0&size=10",
        String.class, savedUser.getId());
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(response);
    String kids = jsonNode.get("content").toString();
    List<SandboxKidResponse> kidsList = mapper.readValue(kids, new TypeReference<>() {
    });

    IntStream
        .range(0, kidsList.size())
        .boxed()
        .forEach(index -> {
          Assertions.assertEquals(kidsList.get(index).getAge(), userKids.get(index).getAge());
          Assertions.assertEquals(kidsList.get(index).getFirstName(), userKids.get(index).getFirstName());
          Assertions.assertNotNull(kidsList.get(index).getId());
          Assertions.assertEquals(kidsList.get(index).getParentInfo().getId(), savedUser.getId());
        });
  }


  @Test
  void createKid() {
    var user = new User();
    user.setEmailAddress("test");
    user.setFirstName("test");
    user.setLastName("test");
    User savedUser = userRepository.save(user);
    EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters());
    SandboxUserKidsCommand creationCommand = easyRandom.nextObject(SandboxUserKidsCommand.class);

    SandboxKidResponse response = this.restTemplate.postForObject(LOCAL_HOST + port + "/users/{userId}/kids",
        creationCommand, SandboxKidResponse.class, savedUser.getId());

    Assertions.assertEquals(response.getAge(), creationCommand.getAge());
    Assertions.assertEquals(response.getFirstName(), creationCommand.getFirstName());
    Assertions.assertEquals(response.getParentInfo().getId(), savedUser.getId());
    Assertions.assertNotNull(response.getId());

  }


 /* @Test
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

  }*/

}
