package dev.alexandrevieira.sm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alexandrevieira.sm.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository <Stock, Long> {
	public Optional<Stock> findByTicker(String tikcer);
	
	public Long deleteByTicker(String ticker);
}
