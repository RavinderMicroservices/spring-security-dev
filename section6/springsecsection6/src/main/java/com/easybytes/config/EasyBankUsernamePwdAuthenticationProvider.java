package com.easybytes.config;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EasyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		/*
		 * commented if else so that this class will allow any password in default profile 
		 * 
		 * */
//		if(passwordEncoder.matches(pwd, userDetails.getPassword())) {
			// fetch age details and perform validation to check if age > 18
		return new UsernamePasswordAuthenticationToken(userName,pwd,userDetails.getAuthorities());
//		}else {
//			throw new BadCredentialsException("Invalid credentials");
//		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
