package com.distributore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "distributor")
public class Distributor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "is_working")
	private boolean isWorking;

	@Column(name = "last_maintenance")
	private LocalDateTime lastMaintenance;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Builder.Default
	@Column(name = "saldo_temporale", precision = 10, scale = 2)
	private BigDecimal saldoTemporale = BigDecimal.ZERO;
	
	@OneToOne(mappedBy = "distributor", cascade = CascadeType.ALL)
	private CashRegister cashRegister;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToMany
	@JoinTable(name = "distributor_products" , 
				joinColumns = @JoinColumn(name = "distributor_id"), 
				inverseJoinColumns = @JoinColumn(name = "product_id")
			)
	private List<Product> products;
	

// --------------------------------------------------------------------------- 

	
	 // METODO PER AGGIUNGERE IMPORTI
    public void addToSaldoTemporale(BigDecimal amount) {
        if (this.saldoTemporale == null) {
            this.saldoTemporale = BigDecimal.ZERO;
        }
        this.saldoTemporale = this.saldoTemporale.add(amount);
    }
    
    // METODO PER RESETTARE IL SALDO
    public void resetSaldoTemporale() {
        this.saldoTemporale = BigDecimal.ZERO;
    }
    
    // METODO PER OTTENERE IL SALDO ATTUALE
    public BigDecimal getSaldoTemporale() {
        return saldoTemporale != null ? saldoTemporale : BigDecimal.ZERO;
    }
    
   
}
