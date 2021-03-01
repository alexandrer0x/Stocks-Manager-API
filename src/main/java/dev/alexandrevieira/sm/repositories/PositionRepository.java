package dev.alexandrevieira.sm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionId;
import dev.alexandrevieira.sm.domain.User;

@Repository
public interface PositionRepository extends JpaRepository<Position, PositionId>{
	public List<Position> findByUser(User user);
}
