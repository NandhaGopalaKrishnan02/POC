package com.connectionHandlerService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.connectionHandlerService.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	
	/* 
	 * This configure method take care of where user data resides so it will look for userdetails service to get user info and here we will 
	   mention which  password encoder spring must use to encode the password.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/ws/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/dl/testAuth").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
		http.authorizeRequests().antMatchers("/**").authenticated();
		http.addFilterBefore(new CustomAuthorizationFilter(getApplicationContext()), UsernamePasswordAuthenticationFilter.class);
	}
	
// If we declare this bean then we have to create userdetails service otherwise it will show bean not found error
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
