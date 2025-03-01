package com.contact.smartcontactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.contact.smartcontactmanager.entity.User;
import com.contact.smartcontactmanager.projection.UserProjection;

@Repository
@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, String>{

}
