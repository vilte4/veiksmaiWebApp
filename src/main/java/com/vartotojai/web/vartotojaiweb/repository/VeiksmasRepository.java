package com.vartotojai.web.vartotojaiweb.repository;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VeiksmasRepository extends CrudRepository<Veiksmas, Integer>{

    List<Veiksmas> findByReiksme(String reiksme);
    Veiksmas findOneByReiksme(String reiksme);

}
