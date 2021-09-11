package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Order;


//clase serve para acessar os dados e salvar
public interface OrderRepository extends JpaRepository<Order, Long> {

	//nesse caso especifico não precisa criar a impelmentação dessa interface. o spring já faz a implementação para o tipo especifico declarado "User"
}
