package dev.alexandrevieira.sm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Stock {
	
	@Getter
	private Long id;
	
	@Getter @Setter
	private String ticker;
	
	@Getter @Setter
	private String company;
	
	@Getter @Setter
	private double price;
	
	@Getter @Setter
	private double previousClosePrice;
	
	@Getter @Setter
	private Date lastUpdated;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
