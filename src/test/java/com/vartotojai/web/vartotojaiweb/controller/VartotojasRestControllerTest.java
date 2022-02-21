package com.vartotojai.web.vartotojaiweb.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.service.VartotojasService;
import com.vartotojai.web.vartotojaiweb.service.VeiksmasService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = VartotojasRestController.class)
public class VartotojasRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VartotojasService service;

    @MockBean
    VeiksmasService veiksmasService;


    @AfterEach
    void tearDown() {
        reset(service);
    }

    @Test
    void testShowVartotojasList() throws Exception {
        List<Vartotojas> vartotojai = new ArrayList<Vartotojas>();
        vartotojai.add(new Vartotojas(1, "Algis", "876547"));
        vartotojai.add(new Vartotojas(2, "Juozas", "111111"));

        when(service.findAll()).thenReturn(vartotojai);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/vartotojai")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "["
                + "{\"id\":1,\"vardas\":\"Algis\",\"telNr\":\"876547\"},"
                + "{\"id\":2,\"vardas\":\"Juozas\",\"telNr\":\"111111\"}"
                + "]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testAddVeiksmasToVartotojas() throws Exception {

        Veiksmas mockPirkimas = new Veiksmas("insert", 1, "01.02");
        when(veiksmasService.add(Mockito.any(Veiksmas.class))).thenReturn(mockPirkimas);

        String veiksmasJson = "{\"reiksme\":\"insert\",\"vartotojoID\":1,\"date\":\"01.02\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/vartotojai/1/veiksmai")
                .content(veiksmasJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/vartotojai/1/veiksmai/insert-1-01.02", response.getHeader(HttpHeaders.LOCATION));

    }

}
