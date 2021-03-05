package dev.alexandrevieira.sm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false)
	private String ticker;
	
	
	private String company;
	
	@Column(nullable=true)
	private double price;
	
	@Column(nullable=true)
	private double previousClosePrice;
	
	private Date lastUpdated;
	
	@ManyToMany(mappedBy="favoriteStocks")
	@JsonIgnore
	private Set<User> users = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.stock")
	@JsonIgnore
	private Set<Position> positions = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
	@JsonIgnore
	private List<PositionTrade> positionTrades = new ArrayList<>();
	
	public Stock() {
		
	}
	
	public Stock(String ticker, String company, double price, double previousClosePrice, Date lastUpdated) {
		super();
		this.ticker = ticker;
		this.company = company;
		this.price = price;
		this.previousClosePrice = previousClosePrice;
		this.lastUpdated = lastUpdated;
	}



	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public List<PositionTrade> getPositionTrades() {
		return positionTrades;
	}

	public void setPositionTrades(List<PositionTrade> positionTrades) {
		this.positionTrades = positionTrades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
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
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [ticker=" + ticker + ", company=" + company + ", price=" + price + ", previousClosePrice="
				+ previousClosePrice + ", lastUpdated=" + lastUpdated + ", users=" + users + ", positions=" + positions
				+ ", positionTrades=" + positionTrades + "]";
	}
	
}
