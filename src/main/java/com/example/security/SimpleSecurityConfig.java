package com.example.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter{

	static final Logger logger = LogManager.getLogger(SimpleSecurityConfig.class.getName());
	
	@Override
	protected void configure(HttpSecurity http){
		try {
			http
				.csrf().disable()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.httpBasic();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth){
		try {
			auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
}
