package com.example.course.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.course.entities.Order;
import com.example.course.entities.Product;

//No paradigma orientado a objetos eu não tenho o conceito de chave primaria composta; o atributo identificador do objeto é um só. vou ter que criar uma clase auxiliar para representar o par produto-pedido que quem vai identificar OrderItem.
@Embeddable //relaciona com OrderItem (EmbeddedId)
public class OrderItemPK implements Serializable{
	private static final long serialVersionUID = 1L;

	
	//pedido e produto serao ManyToOne, com pedido e produto //1 ref para produto e 1 referencia para pedido
	@ManyToOne
	@JoinColumn(name = "order_id") //nome da chave estrangeira no bd tabela relacional
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id") 
	private Product product;
	
	//essa clase não tem construtor
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	//preciso declarar produto e pedido; pq são os 2 que identificam o item // para comparar item de pedido eu preciso comparar os 2 juntos.
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
	

	
	
}
