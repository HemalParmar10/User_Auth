package com.user.service;

import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.dto.UserUpdateDto;
import com.user.exception.ResourceNotFoundException;
import com.user.model.User;
import com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserResponseDto registerUser(UserRequestDto dto) {
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
		}

		User user = new User();
		user.setName(dto.getName());
		user.setContact(dto.getContact());
		user.setAddress(dto.getAddress());
		user.setEmail(dto.getEmail());

		// Save encrypted password in database
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		User savedUser = userRepository.save(user);
		return mapToDto(savedUser);
	}

	public UserResponseDto getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return mapToDto(user);
	}

	public List<UserResponseDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	public UserResponseDto updateUser(Long id, UserUpdateDto dto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		user.setName(dto.getName());
		user.setContact(dto.getContact());
		user.setAddress(dto.getAddress());

		User updatedUser = userRepository.save(user);
		return mapToDto(updatedUser);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		userRepository.delete(user);
	}

	private UserResponseDto mapToDto(User user) {
		return new UserResponseDto(
				user.getId(),
				user.getName(),
				user.getContact(),
				user.getAddress(),
				user.getEmail()
		);
	}
}