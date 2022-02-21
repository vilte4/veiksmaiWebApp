package com.vartotojai.web.vartotojaiweb.controller;

import java.net.URI;
import java.util.List;

import com.vartotojai.web.vartotojaiweb.model.Veiksmas;
import com.vartotojai.web.vartotojaiweb.service.VeiksmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vartotojai.web.vartotojaiweb.model.Vartotojas;
import com.vartotojai.web.vartotojaiweb.service.VartotojasService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/vartotojai")
@RestController
public class VartotojasRestController {

	@Autowired
	VartotojasService service;

	@Autowired
	VeiksmasService veiksmasService;

	// localhost:8080/vartotojai
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<Vartotojas>> vartotojaiJson() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	// localhost:8080/vartotojai/2
	@GetMapping(path="/{vartotojoID}", produces = {"application/json"})
	public ResponseEntity<Vartotojas> vartotojasById(@PathVariable int vartotojoID) {
		return new ResponseEntity<>(service.findById(vartotojoID), HttpStatus.OK);
	}


	@PostMapping(path="/{vartotojoID}/veiksmai")
	public ResponseEntity<Void> addVeiksmasToVartotojas(@PathVariable String vartotojoID, @RequestBody Veiksmas newVeiksmas) {

		Veiksmas v = veiksmasService.add(newVeiksmas);

		if (v == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(v.getReiksme()+"-"+v.getVartotojoID()+"-"+v.getDate()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Void> addVartotojas(@RequestBody Vartotojas newVartotojas) {

		Vartotojas v = service.add(newVartotojas);

		if (v == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(v.getID()).toUri();

		return ResponseEntity.created(location).build();
	}


}
