package com.example.demo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/products")
	public List<Product> list() {
		return service.listAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id){
		try {
			Product product = service.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/products")
	public void add(@RequestBody Product product) {
		service.save(product);
	}
	
	@DeleteMapping("/productsd/{id}")
	public void delete(@PathVariable Integer id) {
		try {
			service.delete(id);
		}
		catch(NoSuchElementException e) {
			System.out.println("no such id found");
		}
	}
	
	@PutMapping("/products")
	public void update(@RequestBody Product p) {
		service.save(p);
	}
	
}
