package dev.alexandrevieira.sm.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionId;
import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.BrokerRepository;
import dev.alexandrevieira.sm.repositories.PositionRepository;
import dev.alexandrevieira.sm.repositories.StockRepository;
import dev.alexandrevieira.sm.repositories.UserRepository;


@Service
public class DBService {
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private BrokerRepository brokerRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public void instantiateTestDatabase() {
		Stock stock1 = new Stock(null, "SLCE3", "SLC Agrícola", 22.50, 23.80, new Date(System.currentTimeMillis()));
		Stock stock2 = new Stock(null, "LWSA3", "Locaweb", 28.70, 29.85, new Date(System.currentTimeMillis()));
		Stock stock3 = new Stock(null, "MOVI3", "Movida", 28.70, 29.85, new Date(System.currentTimeMillis()));
		Stock stock4 = new Stock(null, "PRIO3", "PetroRio", 28.70, 29.85, new Date(System.currentTimeMillis()));
		Stock stock5 = new Stock(null, "CSAN3", "Cosan", 28.70, 29.85, new Date(System.currentTimeMillis()));

		Broker broker1 = new Broker(Long.valueOf(820), "BB");
		Broker broker2 = new Broker(Long.valueOf(308), "Clear");
		Broker broker3 = new Broker(Long.valueOf(1099), "Inter");

		User user1 = new User(null, "Alexandre", "Vieira");
		User user2 = new User(null, "João", "Silva");
		User user3 = new User(null, "Maria", "Santos");
		
		

		brokerRepository.saveAll(Arrays.asList(broker1, broker2, broker3));

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));
		
		
		Position position1 = new Position(new PositionId(user1.getId(), broker1.getId(), stock2.getId()), user1, stock2, broker1, 100, 13.21);
		Position position2 = new Position(new PositionId(user1.getId(), broker1.getId(), stock4.getId()), user1, stock4, broker1, 200, 14.33);
		Position position3 = new Position(new PositionId(user1.getId(), broker3.getId(), stock5.getId()), user1, stock5, broker3, 100, 13.21);
		Position position4 = new Position(new PositionId(user1.getId(), broker1.getId(), stock2.getId()), user1, stock2, broker1, 100, 15.27);
		
		
		user1.getBrokers().addAll(Arrays.asList(broker1, broker2));
		user2.getBrokers().addAll(Arrays.asList(broker1, broker3));
		user3.getBrokers().addAll(Arrays.asList(broker2, broker3));

		user1.getFavoriteStocks().addAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));
		user2.getFavoriteStocks().addAll(Arrays.asList(stock1, stock3, stock5));
		user3.getFavoriteStocks().addAll(Arrays.asList(stock2, stock4));

		broker1.getUsers().addAll(Arrays.asList(user1, user2));
		broker2.getUsers().addAll(Arrays.asList(user1, user3));
		broker3.getUsers().addAll(Arrays.asList(user2, user3));

		stock1.getUsers().addAll(Arrays.asList(user1, user2));
		stock2.getUsers().addAll(Arrays.asList(user1, user3));
		stock3.getUsers().addAll(Arrays.asList(user1, user2));
		stock4.getUsers().addAll(Arrays.asList(user1, user3));
		stock5.getUsers().addAll(Arrays.asList(user1, user2));
		
		
		
		positionRepository.saveAll(Arrays.asList(position1, position2, position3, position4));

		brokerRepository.saveAll(Arrays.asList(broker1, broker2, broker3));

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));
	}
}
