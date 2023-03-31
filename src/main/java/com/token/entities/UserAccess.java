package com.token.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserAccess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accessId;

    private String accessName;

    private int accessHierarchyLevel;

    private String department;

    private String designation;


}
