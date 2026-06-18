package com.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

	@NotBlank(message = "Name is required")
	@Size(min = 2, message = "Name must be at least 2 characters")
	private String name;

	@NotNull(message = "Contact number is required")
	private Long contact;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
}