package com.distributore.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "distributori")
public class Distributore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "working")
	private boolean isWorking;
	
	@Column(name = "location")
	private Location location;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@Column(name = "prodotti")
	private List<Product> prodotti;
	
	@OneToOne(mappedBy = "distributore", cascade = CascadeType.ALL)
	private CashRegister cashRegister;
	
}
