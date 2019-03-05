package pl.sygnity.converter.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="RATE_SEQ", sequenceName = "rate_sequence")
public class Rate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RATE_SEQ")
	private Integer id;
	
    @ManyToOne
    @JoinColumn
	private Currency currency;
	
	@Column(nullable=false)
	private Double value;
	
	@Column(nullable=false)
	private LocalDate date;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public Rate(Currency currency, Double value, LocalDate date) {
		super();
		this.currency = currency;
		this.value = value;
		this.date = date;
	}
	
	public Rate() {}
	
}
