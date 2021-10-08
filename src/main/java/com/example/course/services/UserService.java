package com.example.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

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
		//reitirei na aula - 327 tratamento de exceção//return obj.get(); //a operação get do "Optional" retorna um objeto do tipo User que estiver dentro do "Optional"
		return obj.orElseThrow(()->new ResourceNotFoundException(id)); //vou tentar dar o get se não tiver usuario vou lançar a exceção
	}
	
	public User insert(User obj) { // insere no bd um objeto do tipo user
		return repository.save(obj); // save por padrão já retorna o objeto salvo
	}
	
	public void delete(long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) { //deletando usuario inexistente
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());//"could not execute statement; SQL [n/a]; constraint [\"FKPWLYWCCHG27NUBKWTFJP2P7YT: PUBLIC.TB_ORDER FOREIGN KEY(CLIENTE_ID) REFERENCES PUBLIC.TB_USER(ID) (1)\"; SQL statement:\ndelete from tb_user where id=? [23503-200]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"
		}
		
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getOne(id); //"User entity" será uma entidade monitorada pelo JPA // GetOne vai instanciar um usuário, mas não vai no BD ainda, vai só deixar um objeto monitorado pelo JPA para eu trabalhar com ele e em seguida poderei fazer uma operação com ele. É melhor que utilizar o findById. ele necessariamente vai no bd e traz o objeto para gente;
		try {
			updateData(entity, obj); //vou ter que atualizar o "entity" com os dados que vieram no "obj"	
			return repository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) { //atualizar dados do entity com o que chegou no obj
		entity.setName(obj.getName()); 
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
