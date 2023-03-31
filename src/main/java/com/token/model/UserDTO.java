package com.token.model;

import java.util.Date;

import com.token.entities.Profile;
import com.token.entities.UserAccess;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class UserDTO {

	
	private String userId;
	private String userName;
	private String password;
	private String role;
	private Date userCreatedDate;

	private UserAccessDTO userAccess;

	private ProfileDTO profile;

}
