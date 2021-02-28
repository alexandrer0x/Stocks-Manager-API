package dev.alexandrevieira.sm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(Long id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.orElse(null);
	}
}
