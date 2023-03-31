package com.token.controller;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.token.model.LoginDTO;
import com.token.model.UserDTO;
import com.token.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<?> addUserDetails( @RequestBody  UserDTO userDTO) {
		String message = this.userService.addDetails(userDTO);
		return ResponseEntity.ok(message);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getDetailsById(@PathVariable Integer userId) {
		UserDTO userDetailsById = this.userService.getUserDetailsById(userId);
		return ResponseEntity.ok(userDetailsById);
	}

	@PostMapping("/token/{id}")
	public ResponseEntity<?> getToken(@RequestBody LoginDTO loginDTO, @PathVariable Integer id)
			throws NotFoundException {

		UserDTO userDetails = this.userService.getUserDetailsById(id);

		if (!userDetails.getUserName().equals(loginDTO.getUsername())
				&& !userDetails.getUserId().equals(loginDTO.getUserId()))
			throw new NotFoundException();

		String token = this.userService.generateToken(id);
		return ResponseEntity.ok(token);
	}

}
