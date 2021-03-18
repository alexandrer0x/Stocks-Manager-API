package dev.alexandrevieira.sm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.alexandrevieira.sm.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByEmailIgnoreCase(String email);
	
	@Query("select u.id from User u where u.email = ?1")
	public Long getIdByEmail(String email);
}
