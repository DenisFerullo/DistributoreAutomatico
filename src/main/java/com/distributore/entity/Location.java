package com.distributore.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "locations")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="via" ,nullable = false, length = 60)
	private String via;
	
	@Column(name="numero_civico", length = 8)
	private String numeroCivico;
	
	@Column(name="cap",nullable = false,length = 5)
	private String cap;
	
	@Column(name="comune",nullable = false, length = 60)
	private String comune;
	
	@Column(name="provincia",nullable = false, length = 2)
	private String provincia;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp  
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
}
