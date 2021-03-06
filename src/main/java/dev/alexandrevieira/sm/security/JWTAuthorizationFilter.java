package dev.alexandrevieira.sm.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.alexandrevieira.sm.resources.exceptions.StandardError;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private UserDetailsService userDetailsService;
	
	private JWTUtil jwtUtil;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, 
									JWTUtil jwtUtil, 
									UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, 
									FilterChain chain) throws IOException, ServletException {
		String prefix = "Bearer ";
		String header = request.getHeader("Authorization");
		
		
		if(header == null || !header.startsWith(prefix)) {
			//se nao tiver o header || se nao estiver no formato esperado
			setResponse(response, HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
		}
		else {
			//se estivear no formato esperado
			String token = header.substring(prefix.length());
			
			UsernamePasswordAuthenticationToken auth = getAuthentication(token);
			
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				chain.doFilter(request, response);
			}else {
				setResponse(response, HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
			}
		}
	}
	
	private void setResponse(HttpServletResponse response, HttpStatus status, String msg) throws JsonProcessingException, IOException {
		StandardError err = new StandardError(status.value(), msg, System.currentTimeMillis());
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonStr = mapper.writeValueAsString(err);
		
		response.setStatus(err.getStatus());
		response.addHeader("Content-Type", "application/json");
		response.getWriter().write(jsonStr);
		response.getWriter().flush();
		response.getWriter().close();
		
	}


	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(this.jwtUtil.tokenIsValid(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}
