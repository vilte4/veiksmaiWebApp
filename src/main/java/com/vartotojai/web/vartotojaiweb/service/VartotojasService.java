package com.vartotojai.web.vartotojaiweb.service;

import java.util.ArrayList;
import java.util.List;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.repository.VartotojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VartotojasService {

    @Autowired
    private VartotojasRepository repository;

    @Autowired
    private VeiksmasService service;

    public List<Vartotojas> findAll() {
        return (List<Vartotojas>) repository.findAll();
    }

    public Vartotojas findById(int id) {
        return repository.findById(id).get();
    }

    public int findMaxId() {
        Iterable<Vartotojas> vartotojai = repository.findAll();
        int max = 0;
        for(Vartotojas o : vartotojai) {
            if(o.getID() > max) max = o.getID();
        }
        return max;
    }
    public List<Vartotojas> findByName(String name) {
        List<Vartotojas> vartotojai = new ArrayList<Vartotojas>();
        for(Vartotojas o : repository.findAll()) {
            if(o.getVardas().equals(name))
                vartotojai.add(o);
        }
        return vartotojai;
    }

    public void update (Vartotojas v) {
        repository.save(v);
    }

    public Vartotojas add(Vartotojas v) {
        return repository.save(v);
    }

    public void deleteById (int id) {
        repository.delete(repository.findById(id).get());
        Iterable<Veiksmas> veiksmas = service.findAll();
        for(Veiksmas o : veiksmas) {
            if(o.getVartotojoID() == id) {
                service.delete(o);
            }
        }
    }

    public void delete(Vartotojas v) {
        repository.delete(v);
    }
}
