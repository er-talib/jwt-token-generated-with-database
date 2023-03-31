package com.token.model;

import java.util.Date;

import com.token.entities.Profile;
import com.token.entities.UserAccess;

import lombok.Data;

@Data
public class Token {

	private Integer loginCount;
	private String userName;
	private String password;
	private String userId;
	private String autharty;
	private Date loginTime;
	private Date expiredTimeForToken;

	private UserAccess userAccessDTO;
	private Profile profile;

}
