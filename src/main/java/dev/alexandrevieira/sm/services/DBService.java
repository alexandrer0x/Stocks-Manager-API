package dev.alexandrevieira.sm.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.domain.enums.Profile;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void instantiateTestDatabase() {
		Stock stock1 = new Stock("SLCE3", "SLC Agrícola", 22.50, 23.80, LocalDateTime.now());
		Stock stock2 = new Stock("LWSA3", "Locaweb", 28.70, 29.85, LocalDateTime.now());
		Stock stock3 = new Stock("MOVI3", "Movida", 28.70, 29.85, LocalDateTime.now());
		Stock stock4 = new Stock("PRIO3", "PetroRio", 28.70, 29.85, LocalDateTime.now());
		Stock stock5 = new Stock("CSAN3", "Cosan", 28.70, 29.85, LocalDateTime.now());

		Broker broker1 = new Broker(Long.valueOf(820), "BB");
		Broker broker2 = new Broker(Long.valueOf(308), "Clear");
		Broker broker3 = new Broker(Long.valueOf(1099), "Inter");

		User user1 = new User(null, "Alexandre", "Vieira", "alexandrer0x@hotmail.com", passwordEncoder.encode("alexandre"));
		user1.addProfile(Profile.ADMIN);
		
		User user2 = new User(null, "João", "Silva", "joao@silva", passwordEncoder.encode("joao"));
		User user3 = new User(null, "Maria", "Santos", "maria@santos", passwordEncoder.encode("maria"));

		brokerRepository.saveAll(Arrays.asList(broker1, broker2, broker3));

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));

		Position position1 = new Position(user1, broker1, stock2, 100, 13.21);
		Position position2 = new Position(user1, broker1, stock4, 200, 14.33);
		Position position3 = new Position(user1, broker3, stock5, 100, 13.21);
		Position position4 = new Position(user1, broker1, stock2, 100, 15.27);

		
		user1.getBrokers().addAll(Arrays.asList(broker1, broker2));
		user2.getBrokers().addAll(Arrays.asList(broker1, broker3));
		user3.getBrokers().addAll(Arrays.asList(broker2, broker3));
		
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
		 

		user1.getFavoriteStocks().addAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));
		user2.getFavoriteStocks().addAll(Arrays.asList(stock1, stock3, stock5));
		user3.getFavoriteStocks().addAll(Arrays.asList(stock2, stock4));

		List<PositionTrade> trades = new ArrayList<>();
		
		

		
		brokerRepository.saveAll(Arrays.asList(broker1, broker2, broker3));

		positionRepository.saveAll(Arrays.asList(position1, position2, position3, position4));

		userRepository.saveAll(Arrays.asList(user1, user2, user3));

		stockRepository.saveAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5));

		
		
		for(int i = 0, j=31, k = 132; i < 100; i++, j++, k--) {
			PositionTrade trade = new PositionTrade(null, TradeType.COMPRA, user1, stock5, broker3, new Date(System.currentTimeMillis()),
					100, 5.57, 0, null);
			
			int n = ((int)(Math.random()*30))*100;
			if(n == 0)
				n = 100;
			
			trade.setAmount(n);
			
			double p = Math.random() * 100;
			
			if(p < 3)
				p = p * 10;
			
			p = Math.round(p * 100.0) / 100.0;
			
			trade.setPrice(p);
			
			if(i % 8 == 0 || i % 12 == 0 || i % 31 == 0) {
				trade.setStock(stock1);
			}
			else if(i % 5 == 0 || i % 23 == 0) {
				trade.setStock(stock2);
			}
			else if(i % 7 == 0 || i % 11 == 0 || i % 19 == 0) {
				trade.setStock(stock3);
			}
			
			else if(i % 3 == 0 || i % 13 == 0 || i % 17 == 0 || i % 29 == 0 || i % 53 == 0) {
				trade.setStock(stock4);
			}
			else {
				trade.setStock(stock5);
			}
			
			
			
			if(j % 5 == 0 || j % 8 == 0 || j % 31 == 0) {
				trade.setBroker(broker1);
			}
			
			else if(j % 3 == 0 || j % 7 == 0 || j % 11 == 0 || j % 13 == 0 || j % 37 == 0) {
				trade.setBroker(broker2);
			}
			else {
				trade.setBroker(broker3);
			}
			
			
			if(k % 5 == 0 || k % 8 == 0 || k % 31 == 0) {
				trade.setUser(user1);
			}
			
			else if(k % 3 == 0 || k % 7 == 0 || k % 11 == 0 || k % 13 == 0 || k % 37 == 0) {
				trade.setUser(user2);
			}
			else {
				trade.setUser(user3);
			}
			
			trades.add(trade);
		}
		
		positionTradeRepository.saveAll(trades);
			
	}
}
