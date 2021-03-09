package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.PositionTradeRepository;
import dev.alexandrevieira.sm.repositories.UserRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class PositionTradeService {

	@Autowired
	private PositionTradeRepository repository;
	@Autowired
	private UserRepository userRepository;
	
	public PositionTrade find(Long id) {
		Optional<PositionTrade> opt =  repository.findById(id);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PositionTrade.class.getName()));
	}
	
	public List<PositionTrade> findAllByUserEmail(String userEmail) {
		Optional<User> opt = userRepository.findByEmailIgnoreCase(userEmail);
		
		if(opt.orElse(null) == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Email: " + userEmail + ", Tipo: " + User.class.getName());
		}
		
		List<PositionTrade> trades = repository.findAllByUserEmail(userEmail);
		return trades;
	}
	
	public Page<PositionTrade> findPageByUserEmail(String userEmail, Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAllByUserEmail(userEmail, pageRequest);
	}
}
