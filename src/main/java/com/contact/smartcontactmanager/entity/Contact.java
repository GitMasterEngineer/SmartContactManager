package com.contact.smartcontactmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	@Id
	private String id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phoneNumber;

	@NotBlank(message = "Address is required")
	@Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
	private String address;

	private String picture;

	@Lob
	private String description;

	@NotNull(message = "Favorite status is required")
	private boolean favorite;

	@Size(max = 255, message = "Website link should not exceed 255 characters")
	private String websiteLink;

	@Size(max = 255, message = "LinkedIn link should not exceed 255 characters")
	private String LinkedInLink;

	@Size(max = 255, message = "Instagram link should not exceed 255 characters")
	private String InstagramLink;

	private String cloudinaryImagePublicId;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

}
