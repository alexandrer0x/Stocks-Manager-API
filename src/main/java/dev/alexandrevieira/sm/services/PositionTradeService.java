package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.PositionTradeRepository;
import dev.alexandrevieira.sm.services.exceptions.BusinessRuleException;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class PositionTradeService {

	@Autowired
	private PositionTradeRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private TradingService tradingService;
	@Autowired
	private StockService stockService;
	@Autowired
	private BrokerService brokerService;
	
	
	public PositionTrade find(Long id) {
		Optional<PositionTrade> opt =  repository.findById(id);
		
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PositionTrade.class.getSimpleName()));
	}
	
	public PositionTrade insert(PositionTrade positionTrade) {
		Pair<Boolean, String> isValid = positionTrade.isValid();
		
		if(!isValid.getFirst())
			throw new BusinessRuleException(isValid.getSecond() + " inválido.");
		
		User user = userService.findByEmail(positionTrade.getUser().getEmail());
		Stock stock = stockService.find(positionTrade.getStock().getTicker());
		Broker broker = brokerService.find(positionTrade.getBroker().getId());
		
		positionTrade.setUser(user);;
		positionTrade.setStock(stock);
		positionTrade.setBroker(broker);
		
		
		return tradingService.insertPositionTrade(positionTrade);
	}
	
	public List<PositionTrade> findAllByUserEmail(String userEmail) {
		User user = userService.findByEmail(userEmail);
		
		
		List<PositionTrade> trades = repository.findAllByUserEmailOrderByDateAscTypeAsc(user.getEmail());
		return trades;
	}
	
	public Page<PositionTrade> findPageByUserEmail(String userEmail, Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAllByUserEmail(userEmail, pageRequest);
	}
}
