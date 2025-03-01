package com.contact.smartcontactmanager.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.contact.smartcontactmanager.entity.Contact;

@Repository
@RepositoryRestResource(path = "contact", collectionResourceRel = "contacts")
public interface ContactRepository extends JpaRepository<Contact, String> {

	// if you don't want to expose this API the use @RestResource(exported=false)
	// use it.
	@RestResource(path = "by-email")
	List<Contact> findByEmailContaining(@Param("email") String email, Pageable pageable );
	
	//suppose you think phonenumber is long for url purpose then u can customized it.
	@RestResource(path = "by-phone", rel = "by-phone")
	List<Contact> findByPhoneNumberContainingIgnoringCase(@Param("phone") String phoneNumber, Pageable pageable);

	// If i want search by name then in this way we can customized it
	@RestResource(path = "by-name")
	List<Contact> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

}
