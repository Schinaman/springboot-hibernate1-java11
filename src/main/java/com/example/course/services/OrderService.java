package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.Order;
import com.example.course.repositories.OrderRepository;

//@Component //Registra a classe como componente do Spring e ela poderá ser injetada automaticamente assim como autowired;
//Acho que não precisa pro "UserService" pq ele já extende do JpaRepository; isso mesmo sou fd
//@Repository //Compoenente com semantica mais especifica, registra um repository
@Service //Compoenente com semantica mais especifica, registra um serviço
public class OrderService {

	@Autowired //lê-se UserService depende do UserRepository*
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();  
	}
	
	public Order findByID(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get(); //a operação get do "Optional" retorna um objeto do tipo User que estiver dentro do "Optional"
	}
	
}
