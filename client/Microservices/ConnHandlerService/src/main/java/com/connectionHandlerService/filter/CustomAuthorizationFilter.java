package com.connectionHandlerService.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectionHandlerService.advice.ErrorDetails;
import com.connectionHandlerService.request.ReqToConnService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.connectionHandlerService.JWTUtility.JWTUtility;
import com.auth0.jwt.interfaces.DecodedJWT;


public class CustomAuthorizationFilter extends OncePerRequestFilter{

	private ReqToConnService reqToConnService;

	public CustomAuthorizationFilter(ApplicationContext applicationContext){
		this.reqToConnService = applicationContext.getBean(ReqToConnService.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {

				String token = authorizationHeader.substring(7);
				reqToConnService.setToken(token);
				DecodedJWT decodedJWT = JWTUtility.verifyToken(token);
				String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
				String userName = decodedJWT.getSubject();

				Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

				// convert roles of String to roles of type SimpleGrantedAuthority
				Arrays.stream(roles).forEach(role -> {
					authorities.add(new SimpleGrantedAuthority(role));
				});

				// create authenticated user object
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userName, null, authorities);

				//Store it in securityContextHolder
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println("Authorizing....");
				filterChain.doFilter(request, response);

			} catch (RuntimeException e){

				ErrorDetails error = new ErrorDetails(new Date(), e.getMessage(), HttpStatus.UNAUTHORIZED);
				new ObjectMapper().writeValue(response.getOutputStream(), error);

			}
			
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
	
}
