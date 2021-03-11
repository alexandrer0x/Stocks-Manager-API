package dev.alexandrevieira.sm.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.dto.StockDTO;
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
	public ResponseEntity<List<StockDTO>> findAll() {
		List<Stock> stocks = stockService.findAll();
		List<StockDTO> response = stocks.stream().map(obj -> new StockDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Stock stock) {
		stock = stockService.insert(stock);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(stock.getTicker()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/{ticker}")
	public ResponseEntity<Void> update(@PathVariable String ticker, @RequestBody Stock stock) {
		stock.setTicker(ticker);
		stock = stockService.update(stock);	
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE, value="/{ticker}")
	public ResponseEntity<Stock> delete(@PathVariable String ticker) {
		stockService.delete(ticker);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/quote")
	public ResponseEntity<Stock> getQuote(@RequestParam(value = "ticker") String ticker) {
		Stock stock = stockService.getQuote(ticker);
		
		return ResponseEntity.ok().body(stock);
	}
}
