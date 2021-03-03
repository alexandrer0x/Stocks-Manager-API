package dev.alexandrevieira.sm.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.domain.enums.TradeType;
import dev.alexandrevieira.sm.repositories.BrokerRepository;
import dev.alexandrevieira.sm.repositories.PositionRepository;
import dev.alexandrevieira.sm.repositories.PositionTradeRepository;
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

	@Autowired
	private PositionTradeRepository positionTradeRepository;

	public void instantiateTestDatabase() {
		Stock stock1 = new Stock(null, "SLCE3", "SLC Agrícola", 22.50, 23.80, new Date(System.currentTimeMillis()));
		Stock stock2 = new Stock(null, "LWSA3", "Locaweb", 28.70, 29.85, new Date(System.currentTimeMillis()));
		Stock stock3 = new Stock(null, "MOVI3", "Movida", 28.70, 29.85, new Date(System.currentTimeMillis()));
		Stock stock4 = new Stock(null, "PRIO3", "PetroRio", 28.70, 29.85, new Date(System.currentTimeMillis()));
		Stock stock5 = new Stock(null, "CSAN3", "Cosan", 28.70, 29.85, new Date(System.currentTimeMillis()));

		Broker broker1 = new Broker(Long.valueOf(820), "BB");
		Broker broker2 = new Broker(Long.valueOf(308), "Clear");
		Broker broker3 = new Broker(Long.valueOf(1099), "Inter");

		User user1 = new User(null, "Alexandre", "Vieira", "alexandrer0x@hotmail.com", "alexandre");
		User user2 = new User(null, "João", "Silva", "joao@silva", "joao");
		User user3 = new User(null, "Maria", "Santos", "maria@santos", "maria");

		brokerRepository.saveAll(Arrays.asList(broker1, broker2, broker3));

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));

		Position position1 = new Position(user1, stock2, broker1, 100, 13.21);
		Position position2 = new Position(user1, stock4, broker1, 200, 14.33);
		Position position3 = new Position(user1, stock5, broker3, 100, 13.21);
		Position position4 = new Position(user1, stock2, broker1, 100, 15.27);

		/*
		 * user1.getBrokers().addAll(Arrays.asList(broker1, broker2));
		 * user2.getBrokers().addAll(Arrays.asList(broker1, broker3));
		 * user3.getBrokers().addAll(Arrays.asList(broker2, broker3));
		 * 
		 * user1.getFavoriteStocks().addAll(Arrays.asList(stock1, stock2, stock3,
		 * stock4, stock5)); user2.getFavoriteStocks().addAll(Arrays.asList(stock1,
		 * stock3, stock5)); user3.getFavoriteStocks().addAll(Arrays.asList(stock2,
		 * stock4));
		 * 
		 * broker1.getUsers().addAll(Arrays.asList(user1, user2));
		 * broker2.getUsers().addAll(Arrays.asList(user1, user3));
		 * broker3.getUsers().addAll(Arrays.asList(user2, user3));
		 * 
		 * stock1.getUsers().addAll(Arrays.asList(user1, user2));
		 * stock2.getUsers().addAll(Arrays.asList(user1, user3));
		 * stock3.getUsers().addAll(Arrays.asList(user1, user2));
		 * stock4.getUsers().addAll(Arrays.asList(user1, user3));
		 * stock5.getUsers().addAll(Arrays.asList(user1, user2));
		 */

		user1.getFavoriteStocks().addAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));
		user2.getFavoriteStocks().addAll(Arrays.asList(stock1, stock3, stock5));
		user3.getFavoriteStocks().addAll(Arrays.asList(stock2, stock4));

		PositionTrade pt1 = new PositionTrade(null, TradeType.COMPRA, user1, stock5, broker3, new Date(System.currentTimeMillis()),
				100, 5.57, 0, 0);

		// broker3.getPositionTrades().addAll(Arrays.asList(pt1));
		// stock5.getPositionTrades().addAll(Arrays.asList(pt1));
		// user1.getPositionTrades().addAll(Arrays.asList(pt1));

		brokerRepository.saveAll(Arrays.asList(broker1, broker2, broker3));

		positionRepository.saveAll(Arrays.asList(position1, position2, position3, position4));

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));

		positionTradeRepository.saveAll(Arrays.asList(pt1));
	}
}
