package com.vartotojai.web.vartotojaiweb.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.vartotojai.web.vartotojaiweb.service.VartotojasService;
import com.vartotojai.web.vartotojaiweb.service.VeiksmasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VeiksmasController.class)
class VeiksmasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VeiksmasService service;

    @MockBean
    VartotojasService vartotojasService;
    
    @Test
    void testShowAll() throws Exception {
        List<Veiksmas> veiksmai = new ArrayList<Veiksmas>();
        veiksmai.add(new Veiksmas("insert", 1, "01.02"));
        veiksmai.add(new Veiksmas("update", 1, "01.02"));
        when(service.findAll()).thenReturn(veiksmai);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-veiksmai")
                .accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200
                .andExpect(MockMvcResultMatchers.view().name("list-veiksmai"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-veiksmai.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("veiksmai"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-veiksmas");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("veiksmas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/veiksmas.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("veiksmas"))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas",  hasProperty("ID", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas",  hasProperty("reiksme", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas",  hasProperty("vartotojoID", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("veiksmas",  hasProperty("date", emptyOrNullString())))
                .andReturn();
    }

    @Test
    void testAdd() throws Exception {

        when(service.add(Mockito.any(Veiksmas.class))).thenReturn(new Veiksmas("insert", 1, "01.02"));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-veiksmas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("reiksme", "insert")
                .param("vartotojoID", String.valueOf(0))
                .param("date", "01.02")
                .flashAttr("veiksmas", new Veiksmas());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-veiksmai"));

        verify(service).add(Mockito.any(Veiksmas.class));
    }


}