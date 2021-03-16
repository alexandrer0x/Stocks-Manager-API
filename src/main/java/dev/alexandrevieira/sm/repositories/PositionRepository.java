package dev.alexandrevieira.sm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionPK;
import dev.alexandrevieira.sm.domain.User;

@Repository
public interface PositionRepository extends JpaRepository<Position, PositionPK>{
	public List<Position> findByIdUser(User user);
	
	@Query("select distinct p.id.broker from Position p where p.id.user.id = ?1")
	public List<Broker> findBrokersByUser(Long userId);
}
