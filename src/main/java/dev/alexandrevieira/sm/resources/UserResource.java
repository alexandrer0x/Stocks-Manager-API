package dev.alexandrevieira.sm.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.alexandrevieira.sm.domain.User;
import dev.alexandrevieira.sm.services.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		User user = userService.getUser(id);
		
		if(user == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(user);
	}
}
