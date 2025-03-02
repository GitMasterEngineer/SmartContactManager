package com.contact.smartcontactmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.smartcontactmanager.dto.AuthRequest;
import com.contact.smartcontactmanager.dto.JwtResponse;
import com.contact.smartcontactmanager.dto.RefreshTokenRequest;
import com.contact.smartcontactmanager.entity.User;
import com.contact.smartcontactmanager.repository.UserRepository;
import com.contact.smartcontactmanager.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtService jwtService;
	private final UserRepository userRepository;

	public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtService jwtService, UserRepository userRepository) {

		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		String accesstoken = jwtService.generateToken(request.getEmail(), true);
		String refreshtoken = jwtService.generateToken(request.getEmail(), false);
		User user = userRepository.findByEmail(request.getEmail()).get();
		JwtResponse jwtResponse=new JwtResponse(accesstoken, refreshtoken, user);
		return ResponseEntity.ok(jwtResponse);
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> freshToken(@RequestBody RefreshTokenRequest request){
		
		if(jwtService.validateToken(request.refreshToken())) {
			String userNameFromToken = jwtService.getUserNameFromToken(request.refreshToken());
			//we can use multiple validation
			
			String accesstoken = jwtService.generateToken(userNameFromToken, true);
			String refreshtoken = jwtService.generateToken(request.refreshToken(), false);
			User user = userRepository.findByEmail(userNameFromToken).get();
			JwtResponse jwtResponse=new JwtResponse(accesstoken, refreshtoken, user);
			return ResponseEntity.ok(jwtResponse);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
		
	}
	
	
}
