package com.example.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.course.entities.User;
import com.example.course.services.UserService;

@RestController //Anotation que fala que a classe é um recurso web que é implementado por um controlador Rest //cospe o json // é diz pro Spring Boott que é Request Hanlder 
@RequestMapping(value = "/users") 
public class UserResource {
	
	@Autowired
	private UserService service;

//ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
	@GetMapping //indica que o metodo responde a uma requisição do tipo Get do HTTP
	public ResponseEntity<List<User>> findAll(){ //operação para buscar todos os usuarios do BD; se chamar no postman com o users com o metodo Get (evidenciado em GetMapping) 
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}") //indica que a requisição aceita um "id" dentro da URL
	public ResponseEntity<User> findById(@PathVariable Long id){ //@PathVariable serve para o Spring aceitar o id como parametro que chegara na URL
		User obj = service.findByID(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//
	@PostMapping																										//Quando vou INSERIR um novo recurso eu uso o POST. por isso usarei o PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ 															//retorno é um ResponseEntity<User> //nesse caso o meu endpoint vai receber um objeto do tipo User ((User obj)); <-para dizer que este objeto vai chegar no modulo Json na hora de fazer a requisição e este  Json e será desserealizado para um objeto User do java eu preciso por uma annotation @RequestBody 
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); 	//ta maluco kkk /aula 324 insert // tudo isso para retornar resposta 201
		return ResponseEntity.created(uri).body(obj); 																	//Retorno201//Quando eu insiro um recurso é mais adequado retornar resposta 201; é um retorno especifico do HTTP para dizer que eu criei um novo recurso; por padrão retorna 200; em vez de usar ResponseEntity.ok().body(obj); isso reotrna 200 o ok
																														// created espera um objteo do tipo Location do (URI) pq o padrão http qndo retorno 201 espera-se que a resposta conhtenha um cabeçalho contando o endereço do novo recurso inserido; no SpringBoot a forma padrão de gerar esse endereço é (2 linha acima)
	}
	
	@DeleteMapping (value = "/{id}")							//para deletar no padrão rest utilizamos o Delete
	public ResponseEntity<Void> delete(@PathVariable Long id) { //@PathVariable para reconhecer como uma variavel da minhha URL
		service.delete(id);
		return ResponseEntity.noContent().build();				//preciso retornar a resposta //noContent retorna uma resposta vazia e tbm já trata a resposta do retorno que é 204 quando é resposta vazia
	}															// http://localhost:8080/users/1 ao tentar deletar o usuario1 deu erro 500 pq o usuario1 tem pedidos associados, sendo assim os pedidos ficariam sem clientes e perderiamos a integridade do banco. sendo assim o BD não permiteiu 
}
