package com.example.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_user") 
public class User implements Serializable{ 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //dependnedo do bd tem que mudar, mas pros principais vai funcionar
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore// para dar não loop infinito nas chamada Json pq User instancia Order e Order instancia User; precisa ter em 1 dos lados User ou Order;
	//Lazy Loading = definição. quando vc tem uma associação para muitos User ->*Orders, o JPA por padrão não carrega no Json os muitos; agora se for pra ToOne carrega; pra não estourar memoria e trafego;
	@OneToMany(mappedBy = "client") //Notation que diz que é uma chave estrangeira // mapeamento do cliente na classe Pedido @ManyToOne atributo (User client)
	//@JoinColumn(name = "order_id") //pq que não precisa dessa linha? pq a chave estrangeira é a client esse aqui não é chave estranhgeira da outra tabela(?)
	private List<Order> orders = new ArrayList<>(); //Associação; na especificação do projeto diz para estanciar as coleções (new ArrayList<>() feito na declaração;
	
	
	public User() {
		super();
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
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

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public List<Order> getOrders() {
		return orders;
	}
	
	//HashCode and Equals, por padrão eu deixo só ID, mas se eu quiser comparar um produto com outro baseado em mais de um campo poderia também. "nome" por exemplo.
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ "]";
	}

	
}
