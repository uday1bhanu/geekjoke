package com.uday.geekjoke.controller.test;

import com.uday.geekjoke.GeekjokeApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT, classes={ GeekjokeApplication.class })
public class GeekJokeControllerTest {
    @Value("${local.server.port}")
    private int port;
    private URL base;
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/geekjoke");
        template = new TestRestTemplate().withBasicAuth("ap", "apapp!");
    }

    @Test
    public void shouldGetGeekJoke() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getGeekJokeShouldSendResponseHeaders() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        HttpHeaders httpHeaders = response.getHeaders();
        assertEquals("nosniff", httpHeaders.get("X-Content-Type-Options").get(0));
        assertEquals("1; mode=block", httpHeaders.get("X-XSS-Protection").get(0));
        assertEquals("no-cache, no-store, max-age=0, must-revalidate", httpHeaders.getCacheControl());
        assertEquals("no-cache", httpHeaders.getPragma());
    }

    @After
    public void tearDown() {
        this.base = null;
        template = null;
    }
}
