package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionId;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.PositionRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class PositionService {
	
	@Autowired
	private PositionRepository positionRepository;
	
	public Position getPostion(Long userId, Long brokerId, Long stockId) {
		PositionId positionId = new PositionId(userId, brokerId, stockId);
		Optional<Position> opt = positionRepository.findById(positionId);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " + positionId + ", Tipo: " + Position.class.getName()));
	}
	
	public List<Position> getUserPositions(Long userId) {
		List<Position> positions = positionRepository.findByUser(new User(userId, null, null, null, null));
		
		if(positions == null || positions.isEmpty())
			throw new ObjectNotFoundException(
					"Objeto não econtrado! Id: " + userId + ", Tipo: " + Position.class.getName());
		
		return positions;
	}
}
