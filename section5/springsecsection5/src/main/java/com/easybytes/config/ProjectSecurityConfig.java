package com.easybytes.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		http.csrf(csrfConfig -> csrfConfig.disable())
		.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/myAccounts","/myLoans","/myCards","/myBalance").authenticated()
				.requestMatchers("/contacts","/notices","/error","/register").permitAll() );
		http.formLogin(withDefaults());
//		http.formLogin(flc -> flc.disable());
		http.httpBasic(withDefaults());
//		http.httpBasic(hbc -> hbc.disable());
		return http.build();
	}
	
	
	/*
	 *
	 * below method is commented because we have created a customised userDetails bean implementation in 
	 * EasyBankUserDetailsService so below method need to commented otherwise SpringSecurity will be confused
	 * which userdetails implementation to use
	 */
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public CompromisedPasswordChecker CompromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
	
}
