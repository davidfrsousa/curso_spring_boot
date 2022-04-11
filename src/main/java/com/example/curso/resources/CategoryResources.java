package com.example.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entities.Category;
import com.example.curso.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> lista = categoryService.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id){
		Category category = categoryService.findById(id);
		return ResponseEntity.ok().body(category);
	}
	
}
