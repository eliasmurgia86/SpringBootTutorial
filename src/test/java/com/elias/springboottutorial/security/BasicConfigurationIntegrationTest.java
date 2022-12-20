package com.elias.springboottutorial.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicConfigurationIntegrationTest {

    TestRestTemplate restTemplate;
    URL base;
    @LocalServerPort int port;

    @Before
    public void setUp() throws MalformedURLException{
        restTemplate = new TestRestTemplate("user", "password");
        base = new URL("http://localhost:"+port);
    }

    @Test
    public void whenLoggedUserRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenUserWithWrongCredentials_thenUnauthorizedPage() throws Exception{
        restTemplate = new TestRestTemplate("user","wrongPassword");

        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}