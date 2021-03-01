package dev.alexandrevieira.sm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dev.alexandrevieira.sm.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Bean
	public boolean instantiateDatabase() {
		if(ddlAuto.equals("create") || ddlAuto.equals("create-drop")) {
			dbService.instantiateTestDatabase();
			return true;
		}
		
		return false;
	}
}
