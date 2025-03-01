package com.contact.smartcontactmanager.projection;

import org.springframework.data.rest.core.config.Projection;

import com.contact.smartcontactmanager.entity.User;


@Projection(name="user-projection",types= {User.class})
public interface UserProjection {
	
		String getName();
		String getEmail();
		String getAbout();
}
