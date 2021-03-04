package dev.alexandrevieira.sm.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionPK;
import dev.alexandrevieira.sm.services.PositionService;

@RestController
@RequestMapping(value = "api/positions")
public class PositionResource {
	
	@Autowired
	PositionService positionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Position> find(@RequestBody PositionPK id) {
		Position position = positionService.find(id.getUser().getId(), id.getBroker().getId(), id.getStock().getId());
		
		return ResponseEntity.ok().body(position);
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/user/{userId}")
	public ResponseEntity<List<Position>> findByUser(@PathVariable Long userId) {
		List<Position> positions = positionService.findByUser(userId);
		
		return ResponseEntity.ok().body(positions);
	}
}
