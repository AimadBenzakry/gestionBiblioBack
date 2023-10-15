package com.example.demo.Controller;

import com.example.demo.bean.Livre;
import com.example.demo.dao.LivreDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class LivreControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LivreDao livreDao;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    private String createURLWithPort() {
        return "http://localhost:" + port + "/api/v1/livres";
    }

    @Test
    @Sql(statements = "INSERT INTO livres(id, isbn, titre, auteur) VALUES (50, 'aaa', 'titre50', 'auteur50')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM livres WHERE id='50'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllLivres() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Livre>> response = restTemplate.exchange(
                createURLWithPort().concat("/getAll"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Livre>>(){});
        List<Livre> orderList = response.getBody();
        assert orderList != null;
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(orderList.size(), livreDao.findAll().size());

    }

}