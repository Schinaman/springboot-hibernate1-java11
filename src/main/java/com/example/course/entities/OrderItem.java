package com.example.course.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

 
	@EmbeddedId //vou usar outra notation; pq não explicado, mas acho que é a referencia para OrderItemPK
	private OrderItemPK id = new OrderItemPK(); //precisa instaciar senão fica valendo nulo;
	private Integer quantity;
	private Double price;
	

	public OrderItem() {
	}
	
	public OrderItem(Order order, Product product, Integer quantity, Double price) { // order e product criei na mão 
		super();
		id.setOrder(order);
		id.setProduct(product);
		
		this.quantity = quantity;
		this.price = price;
	}

	//Problema de referencia ciclica. Objeto maluco. pq há uma associação de mão dupla, jackson ficou perdido; Pedido chama Item e Item chama Pedido.
    @JsonIgnore//Em Pedido eu tenho associado "cliente" e "items de pedido" o usuário nós colocamos JsonIgnore para cortar a associação de mão dupla; da mesma forma vou ter que ir no OrderItem e cortar a associação do order item; no OrderItem eu não tenho associação direta. ele tem associação com atributo id da classe OrderItemPK; na plataforma Java Enterprise o que vale é o metodo Get. então eu ponho o JsonIgnore no getOrder, pq é o getOrder que chama o pedido associado ao item de pedido
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
