package dev.alexandrevieira.sm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.UserRepository;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = repository.findByEmailIgnoreCase(username);
		User user = opt.orElse(null);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}

}
