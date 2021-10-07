package com.example.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	//@Transient //para o JPA ignorar e não tentar interpretar o atributo da entity
	@ManyToMany
	@JoinTable(name = "tb_product_category", // Anotation: diz nome da tabela e chaves estrangeiras que associará produto com categoria //tb_product_category: será o nome da tabela no banco de dados  
	  joinColumns = @JoinColumn(name= "product_id"), //joinColumns: to falando que na tabela de Produtos no banco de dados eu vou ter uma chave estrangeira chamada "product_id" que vai conter o id do usuario; para manytomany precisa associar as duas tabelas  
	  inverseJoinColumns = @JoinColumn(name = "category_id")) // definir chave estrangeira da outra entidade, no caso, categoria, se eu tivesse montado na outra classe aqui seria Produto    
	private Set<Category> categories = new HashSet<>(); //Set: representa um conjunto, garante que não vou ter um produto com a mesma categoria mais de 1x// instanciar para garantir que a coleção nao comece nula//Set é uma interface não pode ser instanciado, usando HashSet, classe correspondente a interface
	
	//para entender melhor olhar o relacionamento das instanciações. A OrderItem está intermediando Order e Product, haverá associação entre OrderItem e Product
	@OneToMany(mappedBy = "id.product") //"igual" ao que está em Order. *id vem da classe OrderItemPK e *.product = ao nome do atributo instanciado para produto na clase OrderItemPK 
	private Set<OrderItem> items =new HashSet<>();
	
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

	public Set<Category> getCategories() {
		return categories;
	}

	//o método get precisa retornar uma lista de Order e não de OrderItem; não faz sentido ter Produto retornar os OrderItems atrelados.
	//vou ter que varrer a coleção de orderItem e pegar o order associado a ele
	@JsonIgnore //contrapartida desse ignore está em getProduct da classe Order, comentado lá.
	public Set<Order> getOrders(){
		Set<Order> set =new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}
	/* JsonIgnore aqui vai gerar:
	 * {
    "id": 1,
    "moment": "2019-06-20T19:53:07Z",
    "orderStatus": "PAID",
    "client": {
        "id": 1,
        "name": "Maria Brown",
        "email": "maria@gmail.com",
        "phone": "988888888",
        "password": "123456"
    },
    "item": [
        {
            "quantity": 2,
            "price": 90.5,
            "product": {
                "id": 1,
                "name": "The Lord of the Rings",
                "descrption": "Lorem ipsum dolor sit amet, consectetur.",
                "price": 90.5,
                "imgUrl": "",
                "categories": [
                    {
                        "id": 2,
                        "name": "Books"
                    }
                ]
            }
        },
        {
            "quantity": 1,
            "price": 1250.0,
            "product": {
                "id": 3,
                "name": "Macbook Pro",
                "descrption": "Nam eleifend maximus tortor, at mollis.",
                "price": 1250.0,
                "imgUrl": "",
                "categories": [
                    {
                        "id": 3,
                        "name": "Computers"
                    }
                ]
            }
        }
    ]
}
	 */
	
	
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
