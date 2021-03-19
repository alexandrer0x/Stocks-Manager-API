package dev.alexandrevieira.sm.resources;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.alexandrevieira.sm.domain.Stock;
import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.dto.UserNewDTO;
import dev.alexandrevieira.sm.services.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserNewDTO> find(@PathVariable Long id) {
		User user = userService.find(id);
		UserNewDTO dto = new UserNewDTO(user);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/email")
	public ResponseEntity<UserNewDTO> find(@RequestParam(value = "user") String email) {
		User user = userService.findByEmail(email);
		UserNewDTO dto = new UserNewDTO(user);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody UserNewDTO userNewDTO) {
		User user = userService.fromDTO(userNewDTO);
		user = userService.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(user.getId()).toUri();
		
		ResponseEntity<User> response = ResponseEntity.created(uri).body(user);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/favorites")
	public ResponseEntity<Set<Stock>> findAllFavorites(@RequestParam(value = "user") String userEmail) {
		return ResponseEntity.ok().body(userService.getFavorites(userEmail));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/favorites")
	public ResponseEntity<Void> insertFavorite(
			@RequestParam(value = "user") String userEmail,
			@RequestParam(value = "stock") String stockTicker) {
		
		userService.insertFavorite(userEmail, stockTicker);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/favorites")
	public ResponseEntity<Set<Stock>> deleteFavorite(
			@RequestParam(value = "user") String userEmail,
			@RequestParam(value = "stock") String stockTicker) {
		
		userService.deleteFavorite(userEmail, stockTicker);
		
		return ResponseEntity.ok().build();
	}
}
