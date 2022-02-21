package com.vartotojai.web.vartotojaiweb.jpa;

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.repository.VartotojasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLineRunnerVartotojas implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerVartotojas.class);

    @Autowired
    private VartotojasRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("~~~~~~ VartotojasCommandLineRunner ~~~~~~");
        repository.save(new Vartotojas("Jonas", "862878"));
        repository.save(new Vartotojas("Lina", "865478"));
        repository.save(new Vartotojas("Gabija", "862128"));
        repository.save(new Vartotojas("Liucija", "862832"));
        repository.save(new Vartotojas("Elze", "856878"));

        for (Vartotojas o : repository.findAll()) {
            log.info(o.toString());
        }

    }
}