package com.vartotojai.web.vartotojaiweb.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class VeiksmasRepositoryTest {

    @Autowired
    private VeiksmasRepository repository;

    @Test
    public void testSave() {
        Veiksmas o = new Veiksmas("insert", 1, "01.02");
        repository.save(o);

        Veiksmas veiksmasByReiksme = repository.findOneByReiksme("insert");

        assertNotNull(veiksmasByReiksme);
        assertEquals("01.02", veiksmasByReiksme.getDate());
    }

    @Test
    public void testFindAll() {
        Veiksmas o = new Veiksmas("insert", 1, "01.02");
        repository.save(o);

        Iterable<Veiksmas> veiksmas = repository.findAll();

        assertNotNull(veiksmas);

        List<Veiksmas> result = new ArrayList<Veiksmas>();
        veiksmas.forEach(result::add);

        assertEquals(1, result.size());
    }

    @Test
    public void testDelete() {
        Veiksmas o = new Veiksmas("insert", 1, "01.02");
        repository.save(o);

        Veiksmas veiksmasByVardas = repository.findOneByReiksme("insert");
        assertNotNull(veiksmasByVardas);

        repository.delete(veiksmasByVardas);

        Iterable<Veiksmas> Veiksmas = repository.findAll();

        List<Veiksmas> result = StreamSupport.stream(Veiksmas.spliterator(), false).collect(Collectors.toList());

        assertEquals(0, result.size());
    }

    @Test
    public void testfindByReiksme() {
        Veiksmas v0 = new Veiksmas("insert", 1, "01.02");
        Veiksmas v1 = new Veiksmas("update", 1, "01.02");
        Veiksmas v2 = new Veiksmas("delete", 1, "01.02");
        repository.save(v0);
        repository.save(v1);
        repository.save(v2);

        Iterable<Veiksmas> Veiksmas = repository.findByReiksme("insert");

        assertNotNull(Veiksmas);

        List<Veiksmas> result = StreamSupport.stream(Veiksmas.spliterator(), false).collect(Collectors.toList());

        assertEquals(1, result.size());
    }
}
