package dev.alexandrevieira.sm;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.repositories.StockRepository;

@SpringBootApplication
public class StocksManagerApplication implements CommandLineRunner {
	@Autowired
	private StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(StocksManagerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * Stock stock1 = new Stock(null, "PETR4", "Petrobrás", 22.50, 23.80, new
		 * Date(System.currentTimeMillis())); Stock stock2 = new Stock(null, "LWSA3",
		 * "Locaweb", 28.70, 29.85, new Date(System.currentTimeMillis()));
		 * 
		 * stockRepository.saveAll(Arrays.asList(stock1, stock2));
		 */
	}}
