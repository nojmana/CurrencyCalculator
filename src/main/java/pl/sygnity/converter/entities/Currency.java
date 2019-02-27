package pl.sygnity.converter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Currency {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private String currencyName;

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