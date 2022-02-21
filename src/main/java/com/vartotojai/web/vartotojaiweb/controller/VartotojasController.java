package com.vartotojai.web.vartotojaiweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.service.VartotojasService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VartotojasController {
	@Autowired
	VartotojasService service;

	public VartotojasController() {
		super();
	}

	public VartotojasController(VartotojasService service) {
		super();
		this.service = service;
	}

	// http://localhost:8080/list-vartotojai
	@GetMapping("/list-vartotojai")
	public String showVartotojai(ModelMap model) {
		model.put("vartotojai", service.findAll());
		return "list-vartotojai";
	}

	// http://localhost:8080/add-vartotojas
	@GetMapping("/add-vartotojas")
	public String showAddPage(ModelMap model) {
		model.addAttribute("vartotojas", new Vartotojas("", ""));
		return "vartotojas";
	}

	// http://localhost:8080/add-vartotojas
	@PostMapping("/add-vartotojas")
	public String add(ModelMap model, @ModelAttribute("vartotojas") Vartotojas v, BindingResult result) {
		if(result.hasErrors()) {
			return "vartotojas";
		}
		service.add(v);
		return "redirect:/list-vartotojai";
	}

	// http://localhost:8080/update-vartotojas/1
	@GetMapping("/update-vartotojas/{vartotojasID}")
	public String showUpdatePage(ModelMap model, @PathVariable int vartotojasID) {
		model.addAttribute("vartotojas", service.findById(vartotojasID));
		return "vartotojas";
	}

	// http://localhost:8080/update-vartotojas/1
	@PostMapping("/update-vartotojas/{vartotojasID}")
	public String update(ModelMap model, @ModelAttribute("vartotojas") Vartotojas v, BindingResult result) {
		if(result.hasErrors()) {
			return "vartotojas";
		}
		service.update(v);
		return "redirect:/list-vartotojai";
	}

	// http://localhost:8080/delete-vartotojas/1
	@GetMapping("/delete-vartotojas/{vartotojasID}")
	public String delete(@PathVariable int vartotojasID) {
		service.deleteById(vartotojasID);
		return "redirect:/list-vartotojai";
	}
}
