package dev.alexandrevieira.sm.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.services.StockService;

@RestController
@RequestMapping(value="/api/stocks")
public class StockResource {
	@Autowired
	private StockService stockService; 
	
	@RequestMapping(method=RequestMethod.GET, value="/{ticker}")
	public ResponseEntity<?> list(@PathVariable String ticker) {
		
		Stock stock = stockService.getStock(ticker);
			
		return ResponseEntity.ok().body(stock);
	}
}
