package com.pojos.orderdetail;

import com.pojos.paymentdetails.PaymentDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@Getter
@Setter

public class OrderDetail {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "food_id", nullable = false)
//	    private Food food; // Assuming Food is another entity

	    @Column(nullable = false)
	    private int quantity;

	    @Column(nullable = false)
	    private String paymode;

	    @Column(nullable = false)
	    private String status = "Placed";
//	    Specifies a persistent field or property of an entity whosevalue is an instance of an embeddable class. The embeddableclass must be annotated as Embeddable.
	    @Embedded
	    private PaymentDetails paymentDetails;
}
