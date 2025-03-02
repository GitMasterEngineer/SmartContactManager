package com.contact.smartcontactmanager.entity.event;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.contact.smartcontactmanager.entity.Role;
import com.contact.smartcontactmanager.entity.User;
import com.contact.smartcontactmanager.repository.RoleRepository;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Logger log = LoggerFactory.getLogger(UserEventHandler.class);

	@HandleBeforeCreate
	public void handleBeforeCreate(User user) {
		log.info("going to create user");
		user.setId(UUID.randomUUID().toString());
		Role role=roleRepository.findById(3L).orElseThrow(()-> new RuntimeException("Role not Found"));
		user.getRoles().add(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
	}
}
