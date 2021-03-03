package dev.alexandrevieira.sm.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.Broker;
import dev.alexandrevieira.sm.services.BrokerService;

@RestController
@RequestMapping(value = "/api/brokers")
public class BrokerResource {

	@Autowired
	private BrokerService brokerService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getBrokers() {
		List<Broker> broker = brokerService.getBrokers();
		return ResponseEntity.ok().body(broker);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBroker(@PathVariable Long id) {
		Broker broker = brokerService.getBroker(id);
		return ResponseEntity.ok().body(broker);
	}
}
