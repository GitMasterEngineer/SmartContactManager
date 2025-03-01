package com.contact.smartcontactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.contact.smartcontactmanager.entity.Contact;
import com.contact.smartcontactmanager.entity.User;
import com.contact.smartcontactmanager.projection.UserProjection;

@Configuration
public class RestConfig {

	// We can change overall configuration with the help of this class
	// RepositoryRestConfiguration
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return new RepositoryRestConfigurer() {
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

				config.setBasePath(AppConstants.REST_BASE_PATH);
				config.setDefaultPageSize(AppConstants.PAGE_SIZE);
				config.setDefaultMediaType(MediaType.APPLICATION_JSON);
				config.exposeIdsFor(User.class, Contact.class);
				config.getProjectionConfiguration().addProjection(UserProjection.class);

			}

		};
	}
}
