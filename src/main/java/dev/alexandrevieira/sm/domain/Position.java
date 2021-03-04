package dev.alexandrevieira.sm.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonIgnore
	private PositionPK id;
	
	private int amount;
	
	private double averageCost;
	
	public Position() {
		
	}

	public Position(User user, Broker broker, Stock stock, int amount, double avgPrice) {
		super();
		this.id = new PositionPK(user, broker, stock);
		this.amount = amount;
		this.averageCost = avgPrice;
	}



	public double positionCost() {
		return amount * averageCost;
	}
	
	public double positionValue() {
		return amount * id.getStock().getPrice();
	}
	
	public Broker getBroker() {
		return this.id.getBroker();
	}
	
	public Stock getStock() {
		return this.id.getStock();
	}
	
	@JsonIgnore
	public User getUser() {
		return this.id.getUser();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
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
		return "Position [id=" + id + ", amount=" + amount + ", averageCost=" + averageCost + "]";
	}

}
