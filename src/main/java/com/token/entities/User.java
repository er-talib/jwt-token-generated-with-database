package com.token.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Slf4j
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id ;
	private String userId ;
	private String userName ;
	private String password ;
	private String role ;
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreatedDate ;
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER  )
	@JoinColumn(name = "fk_userId")
	private UserAccess userAccess ;
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER  )
	@JoinColumn(name = "fk_profileId")
	private Profile profile ;
	
	
	 public static String generateMd5(String input) {
	        try {
	            log.info("Generating hash for password ");

	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger no = new BigInteger(1, messageDigest);
	            StringBuilder hashText = new StringBuilder(no.toString(16));
	            while (hashText.length() < 32) {
	                hashText.insert(0, "0");
	            }
	            return hashText.toString();
	        } catch (NoSuchAlgorithmException e) {
	            log.error("No such algo exception occurred during Generating hash for password");
	            throw new RuntimeException(e);
	        }
	    }

}
