package com.pojos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BaseEntity")
public class BaseEntityPojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
	@CurrentTimestamp
	@Column(name="created_at")
      private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
      private LocalDateTime updatedAt;
}
