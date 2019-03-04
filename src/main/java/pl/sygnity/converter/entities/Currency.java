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
	private String name;
	
	public Currency() {}

	public Currency(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}