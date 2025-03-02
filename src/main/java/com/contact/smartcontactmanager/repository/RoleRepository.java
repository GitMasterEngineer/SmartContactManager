package com.contact.smartcontactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.smartcontactmanager.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
