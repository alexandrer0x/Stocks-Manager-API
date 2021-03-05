package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.repositories.UserRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findaAll() {
		return repository.findAll();
	}
	
	public User find(Long id) {
		//Não remover o lançamento de exceção. Os métodos update() e delete() fazem uso dele
		//Em caso de mudanças, reformular o tratamento de exceção
		Optional<User> opt = repository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	public User insert(User user) {
		user.setId(null);
		return repository.save(user);
	}
	
	public User update(User user) {
		//Chamando find, pois caso não exista o ele já lançará a exceção
		find(user.getId());
		return repository.save(user);
	}
	
	@Transactional
	public void delete(long id) {
		//Chamando find, pois caso não exista o ele já lançará a exceção
		find(id);
		repository.deleteById(id);
	}
}
