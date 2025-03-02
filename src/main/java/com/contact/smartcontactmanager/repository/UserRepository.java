package com.contact.smartcontactmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.contact.smartcontactmanager.entity.User;
import com.contact.smartcontactmanager.projection.UserProjection;

@Repository
@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, String>{
	
	@RestResource(path="by-name",rel="by-email")
	List<User> findByEmailContainingIgnoreCase(@Param("email") String email);
	
	Optional<User> findByEmail(String email);

}
