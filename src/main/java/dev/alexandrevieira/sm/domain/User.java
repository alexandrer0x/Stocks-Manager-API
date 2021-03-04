package dev.alexandrevieira.sm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Email
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.user")
	private Set<Position> positions = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<PositionTrade> positionTrades = new ArrayList<>();
	
	
	@ManyToMany
	@JoinTable(name="favorite_stock",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "stock_id"))
	
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Stock> favoriteStocks = new ArrayList<>();
	
	
	@ManyToMany
	@JoinTable(name="user_broker",
			joinColumns = @JoinColumn(name= "user_id"),
			inverseJoinColumns = @JoinColumn(name = "broker_id"))
	
	private List<Broker> brokers = new ArrayList<>();

	public User() {
		
	}

	public User(Long id, String firstName, String lastName, @Email String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Stock> getFavoriteStocks() {
		return favoriteStocks;
	}

	public void setFavoriteStocks(List<Stock> favoriteStocks) {
		this.favoriteStocks = favoriteStocks;
	}

	public List<Broker> getBrokers() {
		return brokers;
	}

	public void setBrokers(List<Broker> brokers) {
		this.brokers = brokers;
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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", positions=" + positions + ", positionTrades=" + positionTrades
				+ ", favoriteStocks=" + favoriteStocks + ", brokers=" + brokers + "]";
	}
}
