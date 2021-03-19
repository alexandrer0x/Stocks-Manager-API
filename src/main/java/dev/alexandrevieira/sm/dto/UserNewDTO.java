package dev.alexandrevieira.sm.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import dev.alexandrevieira.sm.domain.User;

public class UserNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String firstName;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String lastName;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, max = 20, message = "O tamanho deve ser entre 6 e 20 caracteres")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	public UserNewDTO() {
		
	}
	
	public UserNewDTO(User user) {
		this.email = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lasName) {
		this.lastName = lasName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
