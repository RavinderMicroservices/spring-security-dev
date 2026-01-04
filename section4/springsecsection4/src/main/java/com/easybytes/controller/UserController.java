package com.easybytes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybytes.model.Customer;
import com.easybytes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		try {
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			Customer savedCustomer = customerRepository.save(customer);
			
			if(savedCustomer.getId() > 0) {
				return ResponseEntity.status(HttpStatus.CREATED)
			             .body("given user details are successfully registered");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			             .body("USER REGISTRATION FAILED");
			}
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					             .body("an exception occured"+ex.getMessage());
		}
		
	}

}
