package com.sandboxtask.task.endtoend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  private final String LOCAL_HOST = "http://localhost:";
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private WebApplicationContext context;


  @Test
  public void getUsers() throws Exception {
    ResponseEntity<String> response = this.restTemplate.getForEntity(LOCAL_HOST + port + "/users",
        String.class);
    assertThat(response.getBody()).isEqualTo("Test");
  }


}
