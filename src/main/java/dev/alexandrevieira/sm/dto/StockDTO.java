package dev.alexandrevieira.sm.dto;

import java.io.Serializable;

import dev.alexandrevieira.sm.domain.Stock;

public class StockDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ticker;
	private String company;
	
	public StockDTO() {

	}

	public StockDTO(Stock stock) {
		super();
		this.ticker = stock.getTicker();
		this.company = stock.getCompany();
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
}
