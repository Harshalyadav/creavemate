package com.cravemate.pojos;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="foods")
@NoArgsConstructor
@Getter
@Setter
public class Food extends BaseEntity{

	 @Column(length=100,nullable=false)
	private  String name;
	 
	 @Column(length =255)
	 private String description;
	 
	 @Column(nullable = false)
	 private boolean Veg;
	 
	 @Column(nullable = false)
	 private boolean ContainsEgg;
	 

//	 @Lob
//	 @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	 private byte[] photos;
	 
	 @Lob
	 @Column(nullable = false)
	 private byte[] photos;

	 
	 @Column(nullable = false)
	 private double price;

	public Food(String name, String description, boolean veg, boolean containsEgg, double price) {
		super();
		this.name = name;
		this.description = description;
		Veg = veg;
		ContainsEgg = containsEgg;
		this.price = price;
	}
	 private String category;
	 
//	 @OneToMany(mappedBy = "addOns", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	 private List<AddOn> addOns;
	 @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	 private List<Addon> addOns;

//	 @ManyToOne(fetch = FetchType.LAZY)
//	 @JoinColumn(name = "restaurant_id", nullable = false)
//	 private String restaurant;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "restaurant_id", nullable = false)
	 private Restaurant restaurant;

	 
	 
	 @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	 private List<Review> reviews;


	
	
	
}


