package com.vartotojai.web.vartotojaiweb.repository;

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VartotojasRepository extends CrudRepository<Vartotojas, Integer> {

    List<Vartotojas> findByVardas(String vardas);
    Vartotojas findOneByVardas(String vardas);

}

