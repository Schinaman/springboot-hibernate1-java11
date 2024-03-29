package com.example.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity // jpa diz que é uma entidade do bd relacional
@Table(name = "tb_order") // nome da tabela conflitando com o ORDER do SQL
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;// a partir do java8 surgiu essa que subistiu o Date, pq é mto melhor

	private Integer orderStatus; // "Integer macete"

	// @JsonIgnore se eu usar o JsonIgnore deste lado ele vai trazer os pedidos na
	// chamada do Usuário
	@ManyToOne // Notation que diz que é uma chave estrangeira
	@JoinColumn(name = "cliente_id") // indica a chave estrangeira no BD
	private User client; // Associação //chamei um JsonIgnore em OrderItem pra n dar ruim ref ciclica

	//Lá no meu OrderItem eu tenho o ItemOrder PK, e este sim é que terá uma associação ManyToOne com o pedido. quando for mapear meu order vou ter que fazer o macete abaixo.
	@OneToMany (mappedBy = "id.order") //estou mapeando id.order pq no OrderItem eu tenho o Id, mas quem mapeia de fato é a classe OrderItem PK, por isso preciso chamar pela id
	private Set<OrderItem> items = new HashSet<>(); //chamei um JsonIgnore em OrderItem pra n dar ruim ref ciclica, mas neste cas vai no getOrder; se vc ver no User ele vai direto no atributo (List<Order> orders)
	
	//aula payment
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // nome do atributo da classe dependente //no 1pra1 eu preciso colocar "cascade"; no caso do 1pra1 estamos mapeando as 2 entidades para ter o mesmo ID; se o pedido for codigo 5 o pagamento também terá codigo 5. e Nesse caso de mapear 1pra1 é obrigatório colocar isso daqui também. 
	private Payment payment;
	
	public Order() {
		super();
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	public Set<OrderItem> getItem(){
		return items;
	}
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() { //padrão java EE tem que usar get "GET" (referencia na função getSubTotal em OrderItem
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}
