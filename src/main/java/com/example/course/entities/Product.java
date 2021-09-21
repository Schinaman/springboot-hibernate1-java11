package com.example.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_product") 
public class Product implements Serializable{ 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String descrption;
	private Double price;
	private String imgUrl;
	
	@Transient //para o JPA ignorar e não tentar interpretar o atributo da entity
	private Set<Category> categories = new HashSet<>(); //Set: representa um conjunto, garante que não vou ter um produto com a mesma categoria mais de 1x// instanciar para garantir que a coleção nao comece nula//Set é uma interface não pode ser instanciado, usando HashSet, classe correspondente a interface
	
	public Product() {
	}
	
	public Product(Long id, String name, String descrption, Double price, String imgUrl) { //não coloco a coleção no construtor pq eu já estou instanciando a coleção aqui em cima;
		super();
		this.id = id;
		this.name = name;
		this.descrption = descrption;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategory() {
		return categories;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}


	
	
	
	
	
}
