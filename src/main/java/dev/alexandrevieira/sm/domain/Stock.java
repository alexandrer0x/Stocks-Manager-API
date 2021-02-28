package dev.alexandrevieira.sm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId
	@Column(unique=true)
	private String ticker;
	
	private String company;
	
	private double price;
	
	private double previousClosePrice;
	
	private Date lastUpdated;
	
	@ManyToMany(mappedBy="stocks")
	@JsonBackReference
	private List<User> users = new ArrayList<>();
	
	public Stock() {
		
	}
	
	public Stock(Long id, String ticker, String company, double price, double previousClosePrice, Date lastUpdated) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.company = company;
		this.price = price;
		this.previousClosePrice = previousClosePrice;
		this.lastUpdated = lastUpdated;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Stock other = (Stock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", ticker=" + ticker + ", company=" + company + ", price=" + price
				+ ", previousClosePrice=" + previousClosePrice + ", lastUpdated=" + lastUpdated + ", users=" + users
				+ ", getId()=" + getId() + ", getTicker()=" + getTicker() + ", getCompany()=" + getCompany()
				+ ", getPrice()=" + getPrice() + ", getPreviousClosePrice()=" + getPreviousClosePrice()
				+ ", getLastUpdated()=" + getLastUpdated() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

	
}
