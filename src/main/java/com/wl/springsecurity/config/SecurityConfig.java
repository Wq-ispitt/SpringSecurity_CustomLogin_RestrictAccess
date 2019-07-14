package com.wl.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//add users for in memory authentication
		UserBuilder user = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(user.username("Rachel").password("password").roles("EMPLOYEE"))
		.withUser(user.username("Tasha").password("password").roles("EMPLOYEE", "MANAGER"))
		.withUser(user.username("Kelly").password("password").roles("EMPLOYEE", "DEVELOPER"));
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("DEVELOPER")
			.and().formLogin().loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")  //login form should POST data to this URL for processing
			.permitAll()
			.and().logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}

	
	
	
}
