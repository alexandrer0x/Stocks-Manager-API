package dev.alexandrevieira.sm.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.dto.UserNewDTO;
import dev.alexandrevieira.sm.services.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<User> find(@PathVariable Long id) {
		User user = userService.find(id);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/email")
	public ResponseEntity<UserNewDTO> find(@RequestParam(value = "value") String email) {
		User user = userService.findByEmail(email);
		UserNewDTO dto = new UserNewDTO(user);
		
		return ResponseEntity.ok().body(dto);
	}
}
