package dev.alexandrevieira.sm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonManagedReference
	private List<Position> positions = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="user_stock",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "stock_id"))
	
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonManagedReference
	private List<Stock> stocks = new ArrayList<>();
	
	
	@ManyToMany
	@JoinTable(name="user_broker",
			joinColumns = @JoinColumn(name= "user_id"),
			inverseJoinColumns = @JoinColumn(name = "broker_id"))
	
	@JsonManagedReference
	private List<Broker> brokers = new ArrayList<>();

	public User() {
		
	}
	
	
	
	public User(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public List<Stock> getStocks() {
		return stocks;
	}



	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}



	public List<Broker> getBrokers() {
		return brokers;
	}



	public void setBrokers(List<Broker> brokers) {
		this.brokers = brokers;
	}
	
	public List<Position> getPositions() {
		return positions;
	}



	public void setPositions(List<Position> positions) {
		this.positions = positions;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", positions=" + positions
				+ ", stocks=" + stocks + ", brokers=" + brokers + "]";
	}	
}
