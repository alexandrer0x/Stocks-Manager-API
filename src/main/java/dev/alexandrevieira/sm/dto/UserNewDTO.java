package dev.alexandrevieira.sm.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.alexandrevieira.sm.domain.User;

public class UserNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String firstName;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String lasName;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, max = 20, message = "O tamanho deve ser entre 6 e 20 caracteres")
	@JsonIgnore
	private String password;

	public UserNewDTO() {
		
	}
	
	public UserNewDTO(User user) {
		this.email = user.getUsername();
		this.firstName = user.getFirstName();
		this.lasName = user.getLastName();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasName() {
		return lasName;
	}

	public void setLasName(String lasName) {
		this.lasName = lasName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
