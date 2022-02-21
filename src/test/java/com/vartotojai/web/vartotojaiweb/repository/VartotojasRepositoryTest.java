package com.vartotojai.web.vartotojaiweb.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class VartotojasRepositoryTest {

    @Autowired
    private VartotojasRepository repository;

    @Test
    public void testSave() {
        Vartotojas o = new Vartotojas("Jonas", "87893");
        repository.save(o);

        Vartotojas vartotojasByVardas = repository.findOneByVardas("Jonas");

        assertNotNull(vartotojasByVardas);
        assertEquals("87893", vartotojasByVardas.getTelNr());
    }

    @Test
    public void testFindAll() {
        Vartotojas o = new Vartotojas("Jonas", "87893");
        repository.save(o);

        Iterable<Vartotojas> Vartotojas = repository.findAll();

        assertNotNull(Vartotojas);

        List<Vartotojas> result = new ArrayList<Vartotojas>();
        Vartotojas.forEach(result::add);

        assertEquals(1, result.size());
    }

    @Test
    public void testDelete() {
        Vartotojas o = new Vartotojas("AAA", "BBB");
        repository.save(o);

        Vartotojas vartotojasByVardas = repository.findOneByVardas("AAA");
        assertNotNull(vartotojasByVardas);

        repository.delete(vartotojasByVardas);

        Iterable<Vartotojas> Vartotojas = repository.findAll();

        List<Vartotojas> result = StreamSupport.stream(Vartotojas.spliterator(), false).collect(Collectors.toList());

        assertEquals(0, result.size());
    }

    @Test
    public void testfindByVardas() {
        Vartotojas v0 = new Vartotojas("Jonas", "9874");
        Vartotojas v1 = new Vartotojas("Andrius", "4455");
        Vartotojas v2 = new Vartotojas("Gabija", "3873");
        repository.save(v0);
        repository.save(v1);
        repository.save(v2);

        Iterable<Vartotojas> Vartotojas = repository.findByVardas("Jonas");

        assertNotNull(Vartotojas);

        List<Vartotojas> result = StreamSupport.stream(Vartotojas.spliterator(), false).collect(Collectors.toList());

        assertEquals(1, result.size());
    }
}
