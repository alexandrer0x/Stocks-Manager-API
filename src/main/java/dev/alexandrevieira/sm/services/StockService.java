package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.repositories.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getStocks() {
		return stockRepository.findAll();
	}
	
	public Stock getStock(Long id) {
		Optional<Stock> opt = stockRepository.findById(id);
		return opt.orElse(null);
	}
	
	public Stock getStock(String ticker) {
		Optional<Stock> opt = stockRepository.findByTicker(ticker);
		return opt.orElse(null);
	}
}
