package dev.alexandrevieira.sm.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.domain.Position;
import dev.alexandrevieira.sm.domain.PositionTrade;
import dev.alexandrevieira.sm.domain.enums.TradeType;
import dev.alexandrevieira.sm.repositories.PositionRepository;
import dev.alexandrevieira.sm.repositories.PositionTradeRepository;
import dev.alexandrevieira.sm.services.exceptions.NegativePositionException;

@Service
public class TradingService {
	
	@Autowired
	private PositionTradeRepository positionTradeRepository;
	@Autowired
	private PositionRepository positionRepository;
	
	@Transactional
	public PositionTrade insertPositionTrade(PositionTrade newTrade) {
		List<PositionTrade> allTrades = positionTradeRepository.
				findAllByUserEmailAndStockTickerOrderByDateAscTypeAsc(
						newTrade.getUser().getEmail(), 
						newTrade.getStock().getTicker());
		
		//variavel para controle de quantidade e preco medio
		Position globalPosition = new Position(null, null, null, 0, 0);
		
		List<PositionTrade> before = new ArrayList<>();
		List<PositionTrade> after = new ArrayList<>();
		List<PositionTrade> modifieds = new ArrayList<>();
		
		List<Position> toSave = new ArrayList<>();
		List<Position> toDelete = new ArrayList<>();
		
		//Map com uma posicao (Position) para cada corretora (Broker)
		Map<Long, Position> brokersPosition = new HashMap<>();
		
		//criando uma posicao (Position) para cada corretora (Broker) em que foi realizado transacoes (PositionTrade) 
		//e inserindo no Map
		for(var t : allTrades) {
			Broker b = t.getBroker();
			
			if(!brokersPosition.containsKey(b.getId())) {
				Position position = new Position(t.getUser(), t.getBroker(), t.getStock(), 0, 0);
				brokersPosition.put(b.getId(), position);
			}
			
		}
		
		//inserindo a corretora da transação (newTrade) caso ela ainda não esteja no Map
		if(!brokersPosition.containsKey(newTrade.getBroker().getId())) {
			Broker b = newTrade.getBroker();
			
			if(!brokersPosition.containsKey(b.getId())) {
				Position position = new Position(newTrade.getUser(), newTrade.getBroker(), newTrade.getStock(), 0, 0);
				brokersPosition.put(b.getId(), position);
			}
		}
		
		
		//separa as transacoes a serem processadas antes e depois da nova transação (newTrade) em suas respectivas listas
		for(PositionTrade t : allTrades) {
			if(t.getDate().isBefore(newTrade.getDate())) {
				before.add(t);
			}
			else if(t.getDate().isAfter(newTrade.getDate())) {
				after.add(t);
			}
			else if(t.getType() == TradeType.COMPRA) {
				before.add(t);
			}
			else {
				after.add(t);
			}
		}
		
		//processa transaçẽos anteriores
		for(var t : before) {
			process(t, globalPosition, brokersPosition.get(t.getBroker().getId()));
		}
		
		//processa a nova transação (newTrade)
		process(newTrade, globalPosition, brokersPosition.get(newTrade.getBroker().getId()));
		
		//processa as transações posteriores
		for(var t : after) {
			process(t, globalPosition, brokersPosition.get(t.getBroker().getId()));
			
			if(t.getType() == TradeType.VENDA) {
				modifieds.add(t);
			}
		}
		
		
		//
		Set<Long> keys = brokersPosition.keySet();
		
		for (Long key : keys) {
			Position aux = brokersPosition.get(key);
			
			if(aux.getAmount() > 0) {
				aux.setAverageCost(globalPosition.getAverageCost());
				toSave.add(aux);
			}else {
				toDelete.add(aux);
			}
		}
		
		positionTradeRepository.saveAll(modifieds);
		
		positionRepository.saveAll(toSave);
		positionRepository.deleteAll(toDelete);
		
		
		return positionTradeRepository.save(newTrade);
	}
	
	private void process(PositionTrade t, Position globalPosition, Position brokerPosition) {
		if(t.getType() == TradeType.COMPRA) {
			int globalAmount;
			int brokerAmount;
			double averageCost;
			
			globalAmount = t.getAmount() + globalPosition.getAmount();
			brokerAmount = t.getAmount() + brokerPosition.getAmount();
			
			averageCost = (t.getTotalCost() + globalPosition.getAverageCost() * globalPosition.getAmount()) / (globalPosition.getAmount() + t.getAmount());
			
			globalPosition.setAmount(globalAmount);
			globalPosition.setAverageCost(averageCost);
			
			brokerPosition.setAmount(brokerAmount);
			
		}
		else {
			if(t.getAmount() > globalPosition.getAmount() ) {
				throw new NegativePositionException(
						String.format("Saldo negativo de ações. Ação: %s, Data: %s.", t.getStock().getTicker(), t.getDate()));
			}
			
			if(t.getAmount() > brokerPosition.getAmount() ) {
				throw new NegativePositionException(
						String.format("Saldo negativo de ações. Ação: %s, Data: %s, Corretora: %d - %s.", 
								t.getStock().getTicker(), t.getDate(), t.getBroker().getId(), t.getBroker().getName()));
			}
			
			double result;
			double totalSell = t.getPrice() * t.getAmount() - t.getTradeFee();
			
			result = totalSell - (globalPosition.getAverageCost() * t.getAmount());
			
			t.setTradeResult(result);
			
			globalPosition.setAmount(globalPosition.getAmount() - t.getAmount());
			brokerPosition.setAmount(brokerPosition.getAmount() - t.getAmount());
			
		}
	}
}
