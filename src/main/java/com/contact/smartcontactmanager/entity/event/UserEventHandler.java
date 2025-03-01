package com.contact.smartcontactmanager.entity.event;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.contact.smartcontactmanager.entity.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

	private Logger log = LoggerFactory.getLogger(UserEventHandler.class);

	@HandleBeforeCreate
	public void handleBeforeCreate(User user) {
		log.info("going to create user");
		user.setId(UUID.randomUUID().toString());
	}
}
