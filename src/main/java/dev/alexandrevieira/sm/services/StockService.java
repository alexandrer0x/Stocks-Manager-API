package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.repositories.StockRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;

	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

	public Stock find(String ticker) {
		//Não remover o lançamento de exceção. Os métodos update() e delete() fazem uso dele
		//Em caso de mudanças, reformular o tratamento de exceção
		Optional<Stock> opt = stockRepository.findByTicker(ticker);

		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Ticker: " + ticker + ", Tipo: " + Stock.class.getName()));
	}

	public Stock insert(Stock stock) {
		stock.setTicker(null);
		return stockRepository.save(stock);
	}
	
	public Stock update(Stock stock) {
		//chamando find, pois caso não exista o ele já lançará a exceção
		find(stock.getTicker());
		return stockRepository.save(stock);
	}
	
	@Transactional
	public void delete(String ticker) {
		//chamando find, pois caso não exista o ele já lançará a exceção
		find(ticker);
		stockRepository.deleteByTicker(ticker);
	}
}
