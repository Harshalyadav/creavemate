package com.pojos.paymentdetails;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
	
public class PaymentDetails {
	

    @Column(nullable = false)
    private double itemTotal;

    @Column(nullable = false)
    private double promo;

    @Column(nullable = false)
    private double tax;

}
