package com.EndToEndService.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.EndToEndService.JWTUtility.JWTUtility;
import com.auth0.jwt.interfaces.DecodedJWT;

public class CustomAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
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
			
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
	
}
