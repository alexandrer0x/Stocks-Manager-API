package dev.alexandrevieira.sm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.repositories.PositionTradeRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class PositionTradeService {

	@Autowired
	private PositionTradeRepository positionTradeRepository;
	
	public PositionTrade getPositionTrade(Long id) {
		Optional<PositionTrade> opt =  positionTradeRepository.findById(id);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PositionTrade.class.getName()));
	}
}
