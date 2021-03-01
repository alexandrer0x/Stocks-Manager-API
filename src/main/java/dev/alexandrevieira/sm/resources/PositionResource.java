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
import dev.alexandrevieira.sm.domain.PositionId;
import dev.alexandrevieira.sm.services.PositionService;

@RestController
@RequestMapping(value = "api/positions")
public class PositionResource {
	
	@Autowired
	PositionService positionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getPosition(@RequestBody PositionId id) {
		Position position = positionService.getPostion(id.getUserId(), id.getBrokerId(), id.getStockId());
		
		return ResponseEntity.ok().body(position);
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/user/{userId}")
	public ResponseEntity<?> getUserPositions(@PathVariable Long userId) {
		List<Position> positions = positionService.getUserPositions(userId);
		
		return ResponseEntity.ok().body(positions);
	}
}
