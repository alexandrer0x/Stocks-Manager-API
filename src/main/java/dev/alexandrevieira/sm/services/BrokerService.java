package dev.alexandrevieira.sm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.repositories.BrokerRepository;
import dev.alexandrevieira.sm.services.exceptions.ObjectNotFoundException;

@Service
public class BrokerService {

	@Autowired
	private BrokerRepository repository;
	
	public List<Broker> findAll() {
		return repository.findAll();
	}
	
	public Broker find(Long id) {
		Optional<Broker> opt = repository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Broker.class.getName()));
	}
}
