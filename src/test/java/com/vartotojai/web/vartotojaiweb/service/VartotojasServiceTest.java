package com.vartotojai.web.vartotojaiweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vartotojai.web.vartotojaiweb.repository.VartotojasRepository;
import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.repository.VeiksmasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class VartotojasServiceTest {

	@Mock
	VartotojasRepository repository;

	@InjectMocks
	VartotojasService service;

	@Mock
	VeiksmasRepository veiksmasRepository;

	@DisplayName("Test Find All")
	@Test
	void testFindAll() {
		Vartotojas v = new Vartotojas("", "");
		List<Vartotojas> vartotojai = new ArrayList<>();
		vartotojai.add(v);

		when(repository.findAll()).thenReturn(vartotojai);

		List<Vartotojas> found = service.findAll();

		verify(repository).findAll();

		assertEquals(1, found.size());
	}

	@Test
	void testFindById() {
		Vartotojas v = new Vartotojas("", "");
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(v));

		Vartotojas found = service.findById(1);
		verify(repository).findById(Mockito.anyInt());
		assertNotNull(found);
	}

	@Test
	void testFindByName() {

		Vartotojas v = new Vartotojas("Jonas", "");
		List<Vartotojas> vartotojai = new ArrayList<Vartotojas>();
		vartotojai.add(v);

		when(repository.findAll()).thenReturn(vartotojai);

		List<Vartotojas> found = service.findByName("Jonas");
		verify(repository).findAll();
		assertNotNull(found);
		assertEquals(1, found.size());
	}

	@Test
	void testUpdate() {
		Vartotojas p = new Vartotojas("", "");
		service.update(p);
		verify(repository).save(Mockito.any(Vartotojas.class));
	}

	@Test
	void testAdd() {

		Vartotojas v = new Vartotojas("", "");
		when(repository.save(Mockito.any(Vartotojas.class))).thenReturn(v);

		Vartotojas added = service.add(v);
		assertNotNull(added);
		verify(repository).save(Mockito.any(Vartotojas.class));

	}

	@Test
	void testDelete() {
		Vartotojas v = new Vartotojas("", "");
		service.delete(v);
		verify(repository).delete(Mockito.any(Vartotojas.class));
	}

}

