package dev.alexandrevieira.sm.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Position {
	
	@EmbeddedId
	@JsonBackReference
	private PositionId id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonIgnore
	private User user;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "stock_id", insertable = false, updatable = false)
	@JsonManagedReference
	private Stock stock;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "broker_id", insertable = false, updatable = false)
	@JsonManagedReference
	private Broker broker;
	
	private int amount;
	
	private double avgPrice;
	
	public Position() {
		
	}

	public Position(PositionId id, User user, Stock stock, Broker broker, int amount, double avgPrice) {
		super();
		this.id = id;
		this.user = user;
		this.stock = stock;
		this.broker = broker;
		this.amount = amount;
		this.avgPrice = avgPrice;
	}



	public double positionCost() {
		return amount * avgPrice;
	}
	
	public double positionValue() {
		return amount * stock.getPrice();
	}

	public PositionId getId() {
		return id;
	}

	public void setId(PositionId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

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
		Position other = (Position) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", user=" + user + ", stock=" + stock + ", broker=" + broker + ", amount="
				+ amount + ", avgPrice=" + avgPrice + ", getId()=" + getId() + ", getUser()=" + getUser()
				+ ", getStock()=" + getStock() + ", getBroker()=" + getBroker() + ", getAmount()=" + getAmount()
				+ ", getAvgPrice()=" + getAvgPrice() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}
