package com.cravemate.pojos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Table(name="payment_details")
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PaymentDetail {

    @Column(nullable = false)
    private double itemTotal;

    @Column(nullable = false)
    private double promo;

    @Column(nullable = false)
    private double tax;
}
