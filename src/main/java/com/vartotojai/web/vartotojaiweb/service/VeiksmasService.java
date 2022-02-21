package com.vartotojai.web.vartotojaiweb.service;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.repository.VeiksmasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VeiksmasService {

    @Autowired
    VartotojasService vartotojasService;

    @Autowired
    private VeiksmasRepository repository;

    public List<Veiksmas> findAll() {
        return (List<Veiksmas>) repository.findAll();
    }

    public Veiksmas findById(int id) {
        return repository.findById(id).get();
    }

    public int findMaxId() {
        Iterable<Veiksmas> veiksmai = repository.findAll();
        int max = 0;
        for(Veiksmas o : veiksmai) {
            if(o.getID() > max) max = o.getID();
        }
        return max;
    }
    public List<Veiksmas> findByReiksme(String reiksme) {
        List<Veiksmas> veiksmai = new ArrayList<Veiksmas>();
        for(Veiksmas o : repository.findAll()) {
            if(o.getReiksme().equals(reiksme))
                veiksmai.add(o);
        }
        return veiksmai;
    }

    public void update (Veiksmas v) {
        repository.save(v);
    }

    public Veiksmas add(Veiksmas v) { return repository.save(v);}

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void delete(Veiksmas v) {
        repository.delete(v);
    }
}
