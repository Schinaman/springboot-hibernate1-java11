package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

//@Component //Registra a classe como componente do Spring e ela poderá ser injetada automaticamente assim como autowired;
//Acho que não precisa pro "UserService" pq ele já extende do JpaRepository; isso mesmo sou fd
//@Repository //Compoenente com semantica mais especifica, registra um repository
@Service //Compoenente com semantica mais especifica, registra um serviço
public class UserService {

	@Autowired //lê-se UserService depende do UserRepository*
	private UserRepository repository;
	
	public List<User> findAll(){ //repasso essa função pro resource; lembrando que Service é uma classe intermediaria com a logica de negócio
		return repository.findAll();  
	}
	
	public User findByID(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get(); //a operação get do "Optional" retorna um objeto do tipo User que estiver dentro do "Optional"
	}
	
	public User insert(User obj) { // insere no bd um objeto do tipo user
		return repository.save(obj); // save por padrão já retorna o objeto salvo
	}
	
}
