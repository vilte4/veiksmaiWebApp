package com.vartotojai.web.vartotojaiweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.repository.VeiksmasRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class VeiksmasServiceTest {

	@Mock
	VeiksmasRepository repository;

	@InjectMocks
	VeiksmasService service;
	
	@Test
	void testFindAll() {
		Veiksmas v = new Veiksmas("", 0,"");
		List<Veiksmas> vartotojai = new ArrayList<>();
		vartotojai.add(v);

		when(repository.findAll()).thenReturn(vartotojai);
		List<Veiksmas> found = service.findAll();

		verify(repository).findAll();
		assertEquals(1, found.size());
	}

	@Test
	void testFindById() {
		Veiksmas v = new Veiksmas("", 0,"");
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(v));

		Veiksmas found = service.findById(1);
		verify(repository).findById(Mockito.anyInt());
		assertNotNull(found);
	}

	@Test
	void testFindByName() {

		Veiksmas v = new Veiksmas("insert", 0,"");
		List<Veiksmas> veiksmai = new ArrayList<Veiksmas>();
		veiksmai.add(v);

		when(repository.findAll()).thenReturn(veiksmai);

		List<Veiksmas> found = service.findByReiksme("insert");
		verify(repository).findAll();
		assertNotNull(found);
		assertEquals(1, found.size());
	}

	@Test
	void testUpdate() {
		Veiksmas p = new Veiksmas("", 1,"");
		service.update(p);
		verify(repository).save(Mockito.any(Veiksmas.class));
	}

	@Test
	void testAdd() {

		Veiksmas v = new Veiksmas("", 0,"");
		when(repository.save(Mockito.any(Veiksmas.class))).thenReturn(v);

		Veiksmas added = service.add(v);
		verify(repository).save(Mockito.any(Veiksmas.class));
		assertNotNull(added);
	}

	@Test
	void testDelete() {
		Veiksmas v = new Veiksmas("", 0,"");
		service.delete(v);
		verify(repository).delete(Mockito.any(Veiksmas.class));
	}

	@Test
	void testDeleteById() {
		service.deleteById(1);
		verify(repository).deleteById(Mockito.anyInt());
	}

}