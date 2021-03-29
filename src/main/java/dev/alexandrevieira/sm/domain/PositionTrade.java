package dev.alexandrevieira.sm.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.util.Pair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import dev.alexandrevieira.sm.domain.enums.TradeType;

@Entity
public class PositionTrade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Integer type;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private User user;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "stock_ticker")
	private Stock stock;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "broker_id")
	private Broker broker;
	
	@Column(nullable=false)
	private LocalDate date;
	
	
	private int amount;
	
	private double price;
	
	private double tradeFee;
	
	private Double tradeResult;
	
	public PositionTrade() {
		
	}

	public PositionTrade(Long id, Integer type, User user, Stock stock, Broker broker, LocalDate date, int amount,
			double price, double tradeFee, Double tradeResult) {
		super();
		this.id = id;
		this.type = type;
		this.user = user;
		this.stock = stock;
		this.broker = broker;
		this.date = date;
		this.amount = amount;
		this.price = price;
		this.tradeFee = tradeFee;
		this.tradeResult = tradeResult;
	}
	
	
	public TradeType getType() {
		return TradeType.toEnum(this.type);
	}

	public void setType(TradeType type) {
		this.type = type.getCod();
	}

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(double tradeFee) {
		this.tradeFee = tradeFee;
	}

	public Double getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(Double tradeResult) {
		this.tradeResult = tradeResult;
	}
	
	@JsonIgnore
	public double getTotalCost() {
		return this.amount * this.price + this.tradeFee;
	}
	
	@JsonIgnore
	public Pair<Boolean, String> isValid() {
		if(this.amount <= 0) 
			return Pair.of(false, "Quantidade");
		
		if(this.broker == null)
			return Pair.of(false, "Corretora");
		
		if(this.date == null || this.date.isAfter(LocalDate.now()))
			return Pair.of(false, "Data");
		
		if(this.price <= 0)
			return Pair.of(false, "Preço");
		
		if(this.stock == null)
			return Pair.of(false, "Ação");
		
		if(this.tradeFee < 0)
			return Pair.of(false, "Corretagem");
		
		if(TradeType.toEnum(this.type) == null)
			return Pair.of(false, "Tipo");;
		
		if(this.user == null)
			return Pair.of(false, "Usuário");;
		
		return Pair.of(true, "");
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
		PositionTrade other = (PositionTrade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionTrade [id=" + id + ", type=" + this.getType().getDescription() + ", user=" + user + ", stock=" + stock + ", broker="
				+ broker + ", date=" + date + ", amount=" + amount + ", price=" + price + ", tradeFee=" + tradeFee
				+ ", tradeResult=" + tradeResult + "]";
	}
}
