package pl.sygnity.dimon.converter.dao;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Rate {
	
	@Id
	@GeneratedValue
	private Integer id;
	
    @ManyToOne
    @JoinColumn
	@Column(nullable=false) 
	private Currency currency;
	
	@Column(nullable=false)
	private Double converter;
	
	@Column(nullable=false)
	private LocalDate date;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getConverter() {
		return converter;
	}

	public void setConverter(Double converter) {
		this.converter = converter;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
