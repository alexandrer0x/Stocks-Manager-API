package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionPK;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.PositionRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class PositionService {
	
	@Autowired
	private PositionRepository repository;
	
	public Position find(Long userId, Long brokerId, String stockTicker) {
		PositionPK positionPK = new PositionPK(userId, brokerId, stockTicker);
		Optional<Position> opt = repository.findById(positionPK);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " + positionPK + ", Tipo: " + Position.class.getName()));
	}
	
	public List<Position> findByUser(Long userId) {
		List<Position> positions = repository.findByIdUser(new User(userId, null, null, null, null));
		
		if(positions == null || positions.isEmpty())
			throw new ObjectNotFoundException(
					"Objeto não econtrado! Id: " + userId + ", Tipo: " + Position.class.getName());
		
		return positions;
	}
	
	public List<Broker> findBrokersByUser(Long userId) {
		List<Broker> brokers = repository.findBrokersByUser(userId);
		return brokers;
	}
}
