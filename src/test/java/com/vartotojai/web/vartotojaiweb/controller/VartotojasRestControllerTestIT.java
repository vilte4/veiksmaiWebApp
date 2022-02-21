package com.vartotojai.web.vartotojaiweb.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.vartotojai.web.vartotojaiweb.VeiksmaiWebApplication;
import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.service.VartotojasService;


@SpringBootTest(classes = VeiksmaiWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VartotojasRestControllerTestIT {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    VartotojasService service;

    private Vartotojas Vartotojas;

    @BeforeEach
    void setUp() {
        Vartotojas = service.findAll().get(0);
    }

    @Test
    void test() {
        System.out.println("PORT=" + port);
    }

    @Test
    void testVartotojasById() throws Exception {

        String url = "/vartotojai/2";

        String   responseAsString = restTemplate.getForObject(url, String.class);
        Veiksmas responseAsObject = restTemplate.getForObject(url, Veiksmas.class);

        System.out.println("RESPONSE_1:" + responseAsString);
        System.out.println("RESPONSE_1:" + responseAsObject);

        String expected = "{\"id\":2,\"vardas\":\"Lina\",\"telNr\":\"865478\"}";

        JSONAssert.assertEquals(expected, responseAsString, false);
    }

    @Test
    void testAddVeiksmasToVartotojas() {

        String url = "/vartotojai/1/veiksmai";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Veiksmas v = new Veiksmas("insert", 2, "01.02");

        HttpEntity<Veiksmas> entity = new HttpEntity<Veiksmas>(v, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        assertEquals(201, response.getStatusCodeValue());
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        System.out.println("NEW RESOURCE URI: " + actual);

        assertTrue(actual.contains("/vartotojai/1/veiksmai/insert-2-01.02"));
    }


}