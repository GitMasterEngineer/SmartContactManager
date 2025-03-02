package com.contact.smartcontactmanager.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contact.smartcontactmanager.entity.User;
import com.contact.smartcontactmanager.repository.UserRepository;

@Service
public class UserDetails implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetails(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User not found"));

		return null;
	}

}
