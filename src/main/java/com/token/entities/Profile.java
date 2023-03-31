package com.token.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Profile {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer profileId;

    private String email;

    private String mobile;

    private String pan;

    private String firstName;

    private String lastName;

}
