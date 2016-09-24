package com.chechin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGameCreation() {
        boolean initialBoard[][] =
                {{true, false, false},
                        {true, false, false},
                        {false, false, false}};
        ResponseEntity<Long> response = restTemplate.postForEntity("/games", initialBoard, Long.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }
}
