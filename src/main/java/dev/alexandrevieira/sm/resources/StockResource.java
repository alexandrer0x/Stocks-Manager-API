package dev.alexandrevieira.sm.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.services.StockService;

@RestController
@RequestMapping(value="/api/stocks")
public class StockResource {
	@Autowired
	private StockService stockService; 
	
	@RequestMapping(method=RequestMethod.GET, value="/{ticker}")
	public ResponseEntity<Stock> find(@PathVariable String ticker) {
		
		Stock stock = stockService.find(ticker);
			
		return ResponseEntity.ok().body(stock);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Stock>> findAll() {
		List<Stock> stocks = stockService.findAll();
		return ResponseEntity.ok().body(stocks);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Stock stock) {
		stock = stockService.insert(stock);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(stock.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Stock stock) {
		stock.setId(id);
		stock = stockService.update(stock);	
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{ticker}")
	public ResponseEntity<Stock> delete(@PathVariable String ticker) {
		stockService.delete(ticker);
		return ResponseEntity.noContent().build();
	}
}
