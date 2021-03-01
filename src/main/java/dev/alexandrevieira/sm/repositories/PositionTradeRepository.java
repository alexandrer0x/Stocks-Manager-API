package dev.alexandrevieira.sm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alexandrevieira.sm.domain.PositionTrade;

@Repository
public interface PositionTradeRepository extends JpaRepository<PositionTrade, Long> {

}
