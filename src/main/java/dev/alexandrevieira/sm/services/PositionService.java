package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.PositionRepository;
import dev.alexandrevieira.sm.services.exceptions.AuthorizationException;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class PositionService {
	
	@Autowired
	private PositionRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	
	public Position find(String userEmail, Long brokerId, String stockTicker) {
		Long userId = userService.getUserIdByEmail(userEmail);
		
		if(!authService.isAdmin() && !authService.isTheUser(userId)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Position> opt = repository.find(userId, brokerId, stockTicker);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				String.format(
						"Objeto não econtrado! UserId: %d, BrokerId: %d, StockTicker: %s, Tipo: %s",
						userId, brokerId, stockTicker, Position.class.getName())));
	}
	
	public List<Position> findByUser(String userEmail) {
		
		if(!authService.isAdmin() && !authService.isTheUser(userEmail)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Long userId = userService.getUserIdByEmail(userEmail);
		
		List<Position> positions = repository.findByIdUser(new User(userId, null, null, null, null));
		
		if(positions == null)
			throw new ObjectNotFoundException(
					"Objeto não econtrado! Id: " + userId + ", Tipo: " + User.class.getName());
		
		return positions;
	}
	
	public List<Broker> findAllBrokersByUser(String userEmail) {
		
		if(!authService.isAdmin() && !authService.isTheUser(userEmail)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Long userId = userService.getUserIdByEmail(userEmail);
		List<Broker> brokers = repository.findBrokersByUser(userId);
		return brokers;
	}

	public List<Position> findByUserAndStock(String userEmail, String stockTicker) {
		if(!authService.isAdmin() && !authService.isTheUser(userEmail)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<Position> positions = repository.findByIdUserEmailAndIdStockTicker(userEmail, stockTicker);
		return positions;
	}

	public List<Position> findByUserAndBroker(String userEmail, Long brokerId) {
		if(!authService.isAdmin() && !authService.isTheUser(userEmail)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<Position> positions = repository.findByIdUserEmailAndIdBrokerId(userEmail, brokerId);
		return positions;
	}
}
