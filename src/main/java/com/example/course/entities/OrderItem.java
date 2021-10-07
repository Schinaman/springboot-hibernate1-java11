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
    
	//@JsonIgnore //esse Ignore veio da alteração que eu fiz em Produtos; acho que aqui (mappedBy = "id.product") (?confuso) //Pra não dar recorrencia circular no json
	public Product getProduct() {
		return id.getProduct();
	}
/*// Output se eu usasse o Ignore aqui no getOrder; só que eu não quero ao buscar um produto apareça os pedidos; eu quero que ao buscar um pedido apareça os items do pedidos e para cada item um produto; nesse caso vou inverter; vou Ignorar o getOrder da classe Product
	  {
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
        ],
        "order": [
            {
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
                        "price": 90.5
                    },
                    {
                        "quantity": 1,
                        "price": 1250.0
                    }
                ]
            },
    
    
	public void setOrder(Order order) {
		id.setOrder(order);
	}
*/


	
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
