package com.vartotojai.web.vartotojaiweb.controller;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.service.VartotojasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.vartotojai.web.vartotojaiweb.service.VeiksmasService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VeiksmasController {

    @Autowired
    VeiksmasService service;

    @Autowired
    VartotojasService vartotojasService;

    // http://localhost:8080/list-veiksmai
    @GetMapping("/list-veiksmai")
    public String showVeiksmai(ModelMap model) {
        model.put("veiksmai", service.findAll());
        model.put("vartotojai", vartotojasService.findAll());

        return "list-veiksmai";
    }

    @GetMapping("/add-veiksmas")
    public String showAddPage(ModelMap model) {
        model.addAttribute("veiksmas", new Veiksmas("",0 , ""));
        return "veiksmas";
    }


    @PostMapping("/add-veiksmas")
    public String add(ModelMap model, @ModelAttribute("veiksmas") Veiksmas v, BindingResult result) {
        if(result.hasErrors()) {
            return "veiksmas";
        }
        service.add(v);
        return "redirect:/list-veiksmai";
    }

    @GetMapping("/update-veiksmas/{veiksmasID}")
    public String showUpdatePage(ModelMap model, @PathVariable int veiksmasID) {
        model.addAttribute("veiksmas", service.findById(veiksmasID));
        return "veiksmas";
    }

    @PostMapping("/update-veiksmas/{veiksmasID}")
    public String update(ModelMap model, @ModelAttribute("veiksmas") Veiksmas v, BindingResult result) {
        if(result.hasErrors()) {
            return "veiksmas";
        }
        service.update(v);
        return "redirect:/list-veiksmai";
    }

    @GetMapping("/delete-veiksmas/{veiksmasID}")
    public String delete(@PathVariable int veiksmasID) {
        service.deleteById(veiksmasID);
        return "redirect:/list-veiksmai";
    }

}

