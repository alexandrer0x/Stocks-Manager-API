package dev.alexandrevieira.sm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Broker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	
	@ManyToMany(mappedBy = "brokers")
	@JsonBackReference
	private List<User> users = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.broker")
	@JsonBackReference
	private Set<Position> positions = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "broker")
	@JsonBackReference
	private List<PositionTrade> positionTrades = new ArrayList<>();
	
	public Broker() {
		
	}

	public Broker(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
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
		Broker other = (Broker) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Broker [id=" + id + ", name=" + name + ", users=" + users + ", positions=" + positions
				+ ", positionTrades=" + positionTrades + "]";
	}

	
}
