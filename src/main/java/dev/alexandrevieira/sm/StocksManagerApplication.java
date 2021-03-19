package dev.alexandrevieira.sm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.alexandrevieira.sm.apis.FinnhubService;
import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.services.StockService;

@SpringBootApplication
public class StocksManagerApplication implements CommandLineRunner {
	@Autowired
	FinnhubService finnhubService;
	
	@Autowired
	StockService stockService;

	public static void main(String[] args) {
		SpringApplication.run(StocksManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//loadCompanies();
	}
	
	private void loadCompanies() {
		List<Stock> stocks = finnhubService.loadCompanies();
		
		try {
			stocks = finnhubService.getCompaniesData(stocks);
			stockService.insert(stocks);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
