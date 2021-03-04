package dev.alexandrevieira.sm.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.services.PositionTradeService;

@RestController
@RequestMapping(value = "/api/positionTrades")
public class PositionTradeResource {
	
	@Autowired
	PositionTradeService positionTradeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PositionTrade> find(@PathVariable Long id) {
		PositionTrade trade = positionTradeService.find(id);
		
		return ResponseEntity.ok().body(trade);
	}
}
