package com.vartotojai.web.vartotojaiweb.jpa;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.repository.VeiksmasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerVeiksmas implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerVeiksmas.class);

    @Autowired
    private VeiksmasRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("~~~~~~ VeiksmasCommandLineRunner ~~~~~~");
        repository.save(new Veiksmas("delete", 1, "01.02"));
        repository.save(new Veiksmas("update", 3, "03.01"));
        repository.save(new Veiksmas("insert", 2, "06.04"));
        repository.save(new Veiksmas("update", 1, "05.02"));
        repository.save(new Veiksmas("update", 4, "02.01"));

        for (Veiksmas o : repository.findAll()) {
            log.info(o.toString());
        }

    }
}
