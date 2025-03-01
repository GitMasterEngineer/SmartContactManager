package com.contact.smartcontactmanager.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
		@Id
		private String id;
		@Column(nullable = false)
		@NotBlank(message="User name is required")
		@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
		private String name;
		
		@Column(unique = true, nullable = false)
	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
		private String email;
		
		@NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters long")
		private String password;
		
		@Lob
		private String about;
		
		@Column(length = 1000)
		private String profilePicture;
		
		 @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
		private String phoneNumber;
		 
		@NotNull(message = "Enabled status is required")
		private boolean enabled=false;
		
		@NotNull(message = "Email verification status is required")
		private boolean emailverified=false;
		
		 @NotNull(message = "Phone verification status is required")
		private boolean phoneverified=false;
		
		@Enumerated(value = EnumType.STRING)
		private Providers provider=Providers.SELF; 
		
		
		private String emailToken;
		
		@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
		private Set<Contact> contacts=new LinkedHashSet<>();
		
}
