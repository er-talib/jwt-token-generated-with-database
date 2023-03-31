package com.token.service.impl;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.token.entities.Profile;
import com.token.entities.User;
import com.token.entities.UserAccess;
import com.token.model.ProfileDTO;
import com.token.model.Token;
import com.token.model.UserAccessDTO;
import com.token.model.UserDTO;
import com.token.repositories.UserRepository;
import com.token.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String addDetails(UserDTO userDTO) {
		User user = dtoToEntity(userDTO);
		User userDetails = userRepository.save(user);

		return "User details has been saved successfully..!!!";
	}

	@Override
	public UserDTO getUserDetailsById(Integer userId) {
		User user = this.userRepository.findById(userId).get();
		UserDTO userDetails = entityToDTO(user);
		return userDetails;
	}

	public String generateToken(Integer Id) {

		User userDetails = this.userRepository.findById(Id).get();
		String token = tokenEncode(userDetails);
		return token;
	}

	public String tokenEncode(User user) {
		String secratKey = "DEMO";
		Claims claims = Jwts.claims();
		claims.put("userId", user.getUserId());
		claims.put("userName", user.getUserName());
		claims.put("autharty", user.getRole());
		claims.put("loginCount", claims.size());
		claims.put("loginTime", new Date(System.currentTimeMillis()));
		claims.put("expiredTimeForToken", new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)));
		claims.put("userAccessDTO", user.getUserAccess());
		claims.put("profile", user.getProfile());
		claims.put("password", user.getPassword());

	 return	Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,
				Base64.getEncoder().encode(secratKey.getBytes())).compact();
		
	}

	public User dtoToEntity(UserDTO userDTO) {
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setUserName(userDTO.getUserName());
		user.setUserCreatedDate(new Date());
		user.setRole(userDTO.getRole());
		user.setPassword(User.generateMd5(userDTO.getPassword()));
		Profile profile = new Profile();
		profile.setEmail(userDTO.getProfile().getEmail());
		profile.setFirstName(userDTO.getProfile().getFirstName());
		profile.setLastName(userDTO.getProfile().getLastName());
		profile.setMobile(userDTO.getProfile().getMobile());
		profile.setPan(userDTO.getProfile().getPan());
		user.setProfile(profile);
		UserAccess access = new UserAccess();
		access.setAccessHierarchyLevel(userDTO.getUserAccess().getAccessHierarchyLevel());
		access.setAccessName(userDTO.getUserAccess().getAccessName());
		access.setDepartment(userDTO.getUserAccess().getDepartment());
		access.setDesignation(userDTO.getUserAccess().getDesignation());
		user.setUserAccess(access);

		return user;
	}

	public UserDTO entityToDTO(User user) {
		UserDTO userDTO = new UserDTO();

		userDTO.setUserId(user.getUserId());
		userDTO.setUserName(user.getUserName());
		userDTO.setUserCreatedDate(new Date());
		userDTO.setRole(user.getRole());
		userDTO.setPassword(user.getPassword());
		ProfileDTO profile = new ProfileDTO();
		profile.setEmail(user.getProfile().getEmail());
		profile.setFirstName(user.getProfile().getFirstName());
		profile.setLastName(user.getProfile().getLastName());
		profile.setMobile(user.getProfile().getMobile());
		profile.setPan(user.getProfile().getPan());
		userDTO.setProfile(profile);
		UserAccessDTO access = new UserAccessDTO();
		access.setAccessHierarchyLevel(user.getUserAccess().getAccessHierarchyLevel());
		access.setAccessName(user.getUserAccess().getAccessName());
		access.setDepartment(user.getUserAccess().getDepartment());
		access.setDesignation(user.getUserAccess().getDesignation());
		userDTO.setUserAccess(access);

		return userDTO;
	}

}
