package com.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

	@NotBlank(message = "Name is required")
	@Size(min = 2, message = "Name must be at least 2 characters")
	private String name;

	@NotNull(message = "Contact number is required")
	private Long contact;

	@NotBlank(message = "Address is required")
	private String address;
}