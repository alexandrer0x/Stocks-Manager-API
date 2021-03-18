package dev.alexandrevieira.sm.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.domain.enums.Profile;
import dev.alexandrevieira.sm.services.exceptions.AuthorizationException;

@Service
public class AuthService {
	
	public boolean isTheUser(Long id) {
		User user = getAuthenticatedUser();
		
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return id.equals(user.getId());
	}
	
	public boolean isTheUser(String email) {
		User user = getAuthenticatedUser();
		
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return email.equals(user.getEmail());
	}
	
	public boolean isAdmin() {
		User user = getAuthenticatedUser();
		
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return user.hasRole(Profile.ADMIN);
	}
	
	public User getAuthenticatedUser() {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
