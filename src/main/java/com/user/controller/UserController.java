package com.user.controller;

import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.dto.UserUpdateDto;
import com.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/public/health")
	public String health() {
		return "Application Running";
	}

	@PostMapping("/users/register")
	public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto dto) {
		return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping("/users/email/{email}")
	public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserResponseDto> updateUser(
			@PathVariable Long id,
			@Valid @RequestBody UserUpdateDto dto) {
		return ResponseEntity.ok(userService.updateUser(id, dto));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);

		Map<String, String> response = new HashMap<>();
		response.put("message", "User deleted successfully");
		response.put("deletedUserId", String.valueOf(id));

		return ResponseEntity.ok(response);
	}
}