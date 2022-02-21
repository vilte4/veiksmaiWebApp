package com.vartotojai.web.vartotojaiweb.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.service.VartotojasService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VartotojasController.class)
class VartotojasControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	VartotojasService service;

	@Test
	void testShowAll() throws Exception {
		List<Vartotojas> vartotojai = new ArrayList<Vartotojas>();
		vartotojai.add(new Vartotojas("Jonas", "9744"));
		vartotojai.add(new Vartotojas("Gabija", "8748"));
		when(service.findAll()).thenReturn(vartotojai);

		RequestBuilder rb = MockMvcRequestBuilders
				.get("/list-vartotojai")
				.accept(MediaType.TEXT_HTML);

		MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("list-vartotojai"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-vartotojai.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("vartotojai"))
				.andReturn();
	}

	@Test
	public void testShowAddPage() throws Exception {
		RequestBuilder rb = MockMvcRequestBuilders.get("/add-vartotojas");

		MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("vartotojas"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/vartotojas.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("vartotojas"))
				.andExpect(MockMvcResultMatchers.model().attribute("vartotojas",  hasProperty("ID", notNullValue())))
				.andExpect(MockMvcResultMatchers.model().attribute("vartotojas",  hasProperty("vardas", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("vartotojas",  hasProperty("telNr", emptyOrNullString())))
				.andReturn();
	}

	@Test
	void testAdd() throws Exception {

		when(service.add(Mockito.any(Vartotojas.class))).thenReturn(new Vartotojas("Jonas", "9839"));

		RequestBuilder rb = MockMvcRequestBuilders
				.post("/add-vartotojas")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("vardas", "Jonas")
				.param("telNr", "9839")
				.flashAttr("vartotojas", new Vartotojas());

		mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isFound()) // 302
				.andExpect(MockMvcResultMatchers.view().name("redirect:/list-vartotojai"));

		verify(service).add(Mockito.any(Vartotojas.class));
	}

}

