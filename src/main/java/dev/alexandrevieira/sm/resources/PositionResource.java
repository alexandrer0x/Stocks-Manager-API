package dev.alexandrevieira.sm.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.services.PositionService;
import dev.alexandrevieira.sm.services.UserService;

@RestController
@RequestMapping(value = "api/positions")
public class PositionResource {
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> teste(
			@RequestParam(value = "user", required = true) String userEmail,
			@RequestParam(value = "broker", required = false) Long brokerId,
			@RequestParam(value = "stock", required = false) String stockTicker) throws MissingServletRequestParameterException {
		
		if(brokerId != null && stockTicker != null) {
			//passou todos args
			return findById(userEmail, brokerId, stockTicker);
		}
		else if(brokerId != null) {
			//passou user e broker
			return findByUserAndBroker(userEmail, brokerId);
		}
		else if(stockTicker != null) {
			//passou user e stock
			return findByUserAndStock(userEmail, stockTicker);
		}
		else {
			//passou apenas user
			return findByUser(userEmail, brokerId);
		}
	}
	
	private ResponseEntity<Position> findById(String userEmail, Long brokerId, String stockTicker) {
		Position position = positionService.find(userEmail, brokerId, stockTicker);
		
		return ResponseEntity.ok().body(position);
	}
	
	private ResponseEntity<List<Position>> findByUser(String userEmail, Long brokerId) {
		List<Position> positions = positionService.findByUser(userEmail);
		
		return ResponseEntity.ok().body(positions);
	}
	
	private ResponseEntity<List<Position>> findByUserAndBroker(String userEmail, Long brokerId) {
		List<Position> positions = positionService.findByUserAndBroker(userEmail, brokerId);
		return ResponseEntity.ok().body(positions);
	}
	
	private ResponseEntity<List<Position>> findByUserAndStock(String userEmail, String stockTicker) {
		List<Position> positions = positionService.findByUserAndStock(userEmail, stockTicker);
		return ResponseEntity.ok().body(positions);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/brokers")
	public ResponseEntity<List<Broker>> findUserBrokers(@RequestParam(value = "user") String userEmail) {
		List<Broker> brokers = positionService.findAllBrokersByUser(userEmail);
		return ResponseEntity.ok().body(brokers);//verificar se é o usuário
	}
}
