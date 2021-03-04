package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.repositories.StockRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getStocks() {
		return stockRepository.findAll();
	}
	
	public Stock find(Long id) {
		Optional<Stock> opt = stockRepository.findById(id);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Stock.class.getName()));
	}
	
	public Stock find(String ticker) {
		Optional<Stock> opt = stockRepository.findByTicker(ticker);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Ticker: " + ticker + ", Tipo: " + Stock.class.getName()));
	}
}
