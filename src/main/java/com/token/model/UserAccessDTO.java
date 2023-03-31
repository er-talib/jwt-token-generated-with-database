package com.token.model;

import lombok.Data;

@Data
public class UserAccessDTO {

	private String accessName;

	private int accessHierarchyLevel;

	private String department;

	private String designation;

}
