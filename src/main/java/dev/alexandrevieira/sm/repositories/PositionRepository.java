package dev.alexandrevieira.sm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionId;

public interface PositionRepository extends JpaRepository<Position, PositionId>{

}
