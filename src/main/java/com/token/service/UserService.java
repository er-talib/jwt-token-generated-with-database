package com.token.service;

import com.token.model.UserDTO;

public interface UserService {

	public String addDetails(UserDTO userDTO);

	public UserDTO getUserDetailsById(Integer userId);

	public String generateToken(Integer id);
}
