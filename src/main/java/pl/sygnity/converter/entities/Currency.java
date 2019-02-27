package pl.sygnity.converter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="CURRENCY_SEQ", sequenceName = "currency_sequence")
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CURRENCY_SEQ")
	private Integer id;
	
	@Column(nullable=false)
	private String currencyName;
	
	public Currency() {}

	public Currency(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
}