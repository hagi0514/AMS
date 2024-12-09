package Auction.AMS.auction.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;      
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Auction.AMS.auction.entity.cpo;
import Auction.AMS.auction.service.cpoService;
import payload.endpoint.Endpoint;


@CrossOrigin(origins = Endpoint.URL, maxAge = 3600, allowCredentials = "true")

@RestController
@RequestMapping("api/cpo")
public class cpoController {

@Autowired
private cpoService cposervice;


@GetMapping("/cpoList")

public ResponseEntity<List<cpo>> cpoList() {

	return new ResponseEntity<>(cposervice.cpoList(), HttpStatus.OK);
	
}

@PostMapping("/addCpo")
public ResponseEntity<HttpStatus> addCpo(@RequestBody cpo cpo) {
	try {
		cposervice.addCpo(cpo);
		System.out.println("Added CPO: " + cpo);
		return new ResponseEntity<>(HttpStatus.OK);
	} catch (Exception ex) {
		System.out.println(ex);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@PutMapping("/updateCpo/{id}")
public ResponseEntity<HttpStatus> updateCpo(@PathVariable("id") Long id, @RequestBody cpo cpo) {
	try {
		System.out.println(cpo);
		cpo existingCpo = cposervice.getCpoById(id);
		if (existingCpo != null) {
			cposervice.updateCpo(cpo, id);
			System.out.println("Updated CPO: " + cpo);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If CPO not found
		}
	} catch (Exception ex) {
		System.out.println(ex);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


	
	@PutMapping("/deleteCpo/{id}")
public ResponseEntity<cpo> deleteCpo(@PathVariable("id") Long id, @RequestBody cpo cpo) {
    cposervice.deleteCpo(cpo,id);
    return new ResponseEntity<>(HttpStatus.OK);
}

	@GetMapping("/getCpoById/{id}")
	public ResponseEntity<cpo> getCpoById(@PathVariable("id") Long id) {
		cpo cpo = cposervice.getCpoById(id);
		return new ResponseEntity<>(cpo, HttpStatus.OK);
	}

}
