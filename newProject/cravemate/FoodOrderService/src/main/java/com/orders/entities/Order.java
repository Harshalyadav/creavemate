package com.orders.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private OrderStatus orderStatus;	

	private int orderAmount;

	@CreationTimestamp
	private LocalDateTime orderDateTime;
	
	//the date on which order was actually delivered(status - updated)
	private LocalDateTime deliveryDateTime;	

	private int deliveryCharges;	
	private Long customerId;
	private Long restaurantId;

	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "order", orphanRemoval = true)
	private List<OrderLine> orderLines = new ArrayList<>();
	
	//Order 1--->1 DeliveryAddress 
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="address_id")
	private DeliveryAddress address;	
	

	// helper methods to add n remove the order item
	public void addOrderLine(OrderLine line)
	{
		orderLines.add(line);
		line.setOrder(this);
	}
	public void removeOrderItem(OrderLine line)
	{
		orderLines.remove(line);
		line.setOrder(null);
	}	
	
}
