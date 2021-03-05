package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.dto.StockDTO;
import dev.alexandrevieira.sm.repositories.StockRepository;
import dev.alexandrevieira.sm.services.exceptions.DuplicateEntryException;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class StockService {
	@Autowired
	private StockRepository repository;

	public List<Stock> findAll() {
		return repository.findAll();
	}

	public Stock find(String ticker) {
		//Não remover o lançamento de exceção. Os métodos update() e delete() fazem uso dele
		//Em caso de mudanças, reformular o tratamento de exceção
		Optional<Stock> opt = repository.findByTicker(ticker);

		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Ticker: " + ticker + ", Tipo: " + Stock.class.getName()));
	}

	public Stock insert(Stock stock) {
		Optional<Stock> opt = repository.findByTicker(stock.getTicker());
		
		if(opt.orElse(null) != null) {
			throw new DuplicateEntryException(
					"Entrada duplicada! Ticker: " + stock.getTicker() + ", Tipo: " + Stock.class.getName());
		}
		
		return repository.save(stock);
	}
	
	public Stock update(Stock stock) {
		//Chamando find, pois caso não exista o ele já lançará a exceção
		find(stock.getTicker());
		return repository.save(stock);
	}
	
	@Transactional
	public void delete(String ticker) {
		//Chamando find, pois caso não exista o ele já lançará a exceção
		find(ticker);
		repository.deleteByTicker(ticker);
	}
	
	public Stock fromDTO(StockDTO stockDTO) {
		return new Stock(stockDTO.getTicker(), stockDTO.getCompany(), null, null, null);
	}
}
