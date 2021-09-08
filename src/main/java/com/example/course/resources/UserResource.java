package com.example.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;
import com.example.course.services.UserService;

@RestController //Anotation que fala que a classe é um recurso web que é implementado por um controlador Rest //cospe o json
@RequestMapping(value = "/users") 
public class UserResource {
	
	@Autowired
	private UserService service;

//ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
	@GetMapping //indica que o metodo responde a uma requisição do tipo Get do HTTP
	public ResponseEntity<List<User>> findAll(){ 
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}") //indica que a requisição aceita um "id" dentro da URL
	public ResponseEntity<User> findById(@PathVariable Long id){ //@PathVariable serve para o Spring aceitar o id como parametro que chegara na URL
		User obj = service.findByID(id);
		return ResponseEntity.ok().body(obj);
	}
}
