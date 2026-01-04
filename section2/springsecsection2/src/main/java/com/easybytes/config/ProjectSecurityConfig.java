package com.easybytes.config;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/myAccount","/myLoans","/myCards","/myBalance").authenticated()
				.requestMatchers("/contacts","/notices","/error").permitAll() );
		http.formLogin(withDefaults());
//		http.formLogin(flc -> flc.disable());
		http.httpBasic(withDefaults());
//		http.httpBasic(hbc -> hbc.disable());
		return http.build();
	}
	
	
}
