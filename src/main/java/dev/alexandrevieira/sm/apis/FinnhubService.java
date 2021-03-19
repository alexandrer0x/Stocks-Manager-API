package dev.alexandrevieira.sm.apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.alexandrevieira.sm.config.Values;
import dev.alexandrevieira.sm.domain.Stock;

@Service
public class FinnhubService {
	
	public Stock getFinnhubQuote(Stock stock){
		String format = Values.FINNHUB_BASE_URI + "/quote?symbol=%s.SA&token=%s";
		String key = Values.FINNHUB_KEY;
		String urlStr = String.format(format, stock.getTicker(), key);
		FinnhubQuote quote = new FinnhubQuote();
		
        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlStr)).build();
        HttpResponse<String> response = null;
		
        try {
			response = http.send(request, BodyHandlers.ofString());
			ObjectMapper objectMapper = new ObjectMapper();
			quote = objectMapper.readValue(response.body(), FinnhubQuote.class);
			
			stock.setPrice(quote.getCurrentPrice());
			stock.setPreviousClosePrice(quote.getPreviousClosePrice());
			stock.setLastUpdated(LocalDateTime.now());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
        return stock;
    }	
	
	public List<Stock> loadCompanies() {
		String format = Values.FINNHUB_BASE_URI + "/stock/symbol?exchange=SA&token=%s";
		String key = Values.FINNHUB_KEY;
		String urlStr = String.format(format, key);
		
		List<FinnhubCompanyInfo> companies;
		List<Stock> result = new ArrayList<>();
		
		

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlStr)).build();
        HttpResponse<String> response = null;
		
        try {
			response = http.send(request, BodyHandlers.ofString());
			ObjectMapper objectMapper = new ObjectMapper();
			companies = Arrays.asList(objectMapper.readValue(response.body(), FinnhubCompanyInfo[].class));
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
        companies = companies.stream()
        		.filter(x -> (x.getType().equalsIgnoreCase("common stock") || x.getType().equalsIgnoreCase("unit") ))
        		.collect(Collectors.toList());
        
        for(FinnhubCompanyInfo company : companies) {
        	String symbol = company.getSymbol();
        	
        	if(symbol.endsWith(".SA")) {
        		int n = symbol.length();
        		symbol = symbol.substring(0, n-3);
        		company.setSymbol(symbol);
        		
        		String test = symbol.substring(0, symbol.length()-1);
        		
        		try {
        			Integer.parseInt(test);
        		}
        		catch (NumberFormatException e){
        			result.add(new Stock(company.getSymbol(), company.getDescription(), null, null, null));
        		}
        	}
        }
        
        
        return result;
	}
	
	public List<Stock> getCompaniesData(List<Stock> stocks) throws InterruptedException {
		int i, count = 0, totalCount = 1;
		
		Instant lastStart = Instant.now();
		Stock stock;
		List<String> erros = new ArrayList<String>();
		List<Stock> sucesso = new ArrayList<Stock>();
		
		for(i = 0; i < stocks.size(); i++) {
			if(count == 50) {
				count = 0;
				
				if(lastStart.toEpochMilli() + 60000 - Instant.now().toEpochMilli() > 1000)
					Thread.sleep(lastStart.toEpochMilli() + 60000 - Instant.now().toEpochMilli());
				
				
				lastStart = Instant.now();
			}
			
			stock = stocks.get(i);
			
			String format = Values.FINNHUB_BASE_URI + "/stock/profile2?symbol=%s.SA&token=%s";
			String key = Values.FINNHUB_KEY;
			String urlStr = String.format(format, stock.getTicker(), key);
			
			FinnhubProfile profile;
			
			

	        HttpClient http = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlStr)).build();
	        HttpResponse<String> response = null;
			
	        try {
				response = http.send(request, BodyHandlers.ofString());
				ObjectMapper objectMapper = new ObjectMapper();
				profile = objectMapper.readValue(response.body(), FinnhubProfile.class);
				
				if(profile == null || profile.getName() == null | profile.getTicker() == null) {
					erros.add(stock.getTicker());
					System.out.printf("Elemento %3d com erro: %s\n", totalCount, stock.getTicker());
				}
				else {
					
					stock.setCompany(profile.getName());
					System.out.printf("Elemento %3d processado\n", totalCount);
					System.out.println(stock.toString());
					sucesso.add(stock);
				}
				
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        
	        
			count++;
			totalCount++;
		}
		System.out.println("Erros: " + erros.size());
		System.out.println("Sucesso: " + sucesso.size());
		
		
		StringBuilder sb = new StringBuilder();
		
		for(String e : erros) {
			sb.append(String.format("%S\n", e));
		}
		
		System.out.println(sb.toString());
		
		return sucesso;
	}
}
