package com.example.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;

@RestController //Anotation que fala que a classe é um recurso web que é implementado por um controlador Rest
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping //indica que o metodo responde a uma requisição do tipo Get do HTTP
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
		return ResponseEntity.ok().body(u);
	}
}
