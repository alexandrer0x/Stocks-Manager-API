package dev.alexandrevieira.sm.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.apis.FinnhubService;
import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.dto.StockDTO;
import dev.alexandrevieira.sm.repositories.StockRepository;
import dev.alexandrevieira.sm.services.exceptions.DuplicateEntryException;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;
import dev.alexandrevieira.sm.services.exceptions.ServiceUnavaliableException;

@Service
public class StockService {
	@Autowired
	private StockRepository repository;
	
	@Autowired
	private FinnhubService quoteAPI;
	
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
	
	public void insert(List<Stock> stocks) {
		repository.saveAll(stocks);
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
	
	public Stock getQuote(String ticker) {
		Stock stock = find(ticker);
		
		if(this.shouldUpdate(stock)) {
			stock = quoteAPI.getFinnhubQuote(stock);
			
			if(stock == null) {
				throw new ServiceUnavaliableException(
						"Serviço indisponível, tente mais tarde.");
			}
			
			repository.save(stock);
			return stock;
		}

		return stock;
	}
	
	public Stock fromDTO(StockDTO stockDTO) {
		return new Stock(stockDTO.getTicker(), stockDTO.getCompany(), null, null, null);
	}
	
	private boolean shouldUpdate(Stock stock) {
		final LocalTime minTimeToUpdate = LocalTime.of(10, 0);
		final LocalTime maxTimeToUpdate = LocalTime.of(19, 0);
		
		if(stock.getLastUpdated() == null) {
	    	//nunca foi atualizado e/ou não tem preço
    		return true;
    	}
    
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime updated = stock.getLastUpdated();
    	
    	
    	
    	if(now.toLocalTime().isBefore(minTimeToUpdate)) {
    		if(updated.toLocalDate().isBefore(now.toLocalDate())) {
    			
    			return true;
    		}
    	}
    	else if(now.toLocalTime().isAfter(maxTimeToUpdate)) {
    		if(updated.toLocalDate().isBefore(now.toLocalDate())) {
    			return true;
    		}
    		else if(updated.toLocalDate().equals(now.toLocalDate()) && updated.toLocalTime().isBefore(maxTimeToUpdate)) {
    			return true;
    		}
    		
    	}
    	else if(updated.plusMinutes(5).isBefore(now)) {
    		if(now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
    			if(updated.toLocalDate().isBefore(now.toLocalDate())) {
    				return true;
    			}
    		}
    		else {
    			return true;
    		}
    	}
    	
    	return false;
    }
}
