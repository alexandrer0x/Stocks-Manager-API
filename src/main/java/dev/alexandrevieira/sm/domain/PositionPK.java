package dev.alexandrevieira.sm.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class PositionPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "broker_id")
	private Broker broker;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	public PositionPK() {
		
	}
	
	public PositionPK(User user, Broker broker, Stock stock) {
		super();
		this.user = user;
		this.broker = broker;
		this.stock = stock;
	}
	
	public PositionPK(Long userId, Long brokerId, Long stockId) {
		super();
		this.user = new User();
		this.user.setId(userId);
		this.broker = new Broker();
		this.broker.setId(brokerId);
		this.stock = new Stock();
		this.stock.setId(stockId);;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((broker == null) ? 0 : broker.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		PositionPK other = (PositionPK) obj;
		if (broker == null) {
			if (other.broker != null)
				return false;
		} else if (!broker.equals(other.broker))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionPK [user=" + user + ", broker=" + broker + ", stock=" + stock + "]";
	}
}
