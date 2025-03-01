package com.contact.smartcontactmanager.entity.event;

import java.util.UUID;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.contact.smartcontactmanager.entity.Contact;

@Component
@RepositoryEventHandler(Contact.class)
public class ContactEventHandler {
	
	@HandleBeforeCreate
	public void beforeSave(Contact contact) {
		contact.setId(UUID.randomUUID().toString());
	}
		
}
