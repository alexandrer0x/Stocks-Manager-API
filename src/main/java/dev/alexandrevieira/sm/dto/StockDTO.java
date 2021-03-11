package dev.alexandrevieira.sm.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import dev.alexandrevieira.sm.domain.Stock;

public class StockDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ticker;
	private String company;
	private Double price;
	private Double previousClosePrice;
	private LocalDateTime lastUpdated;
	
	public StockDTO() {

	}

	public StockDTO(Stock stock) {
		super();
		this.ticker = stock.getTicker();
		this.company = stock.getCompany();
		this.price = stock.getPrice();
		this.previousClosePrice = stock.getPreviousClosePrice();
		this.lastUpdated = stock.getLastUpdated();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(Double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}
	
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}
