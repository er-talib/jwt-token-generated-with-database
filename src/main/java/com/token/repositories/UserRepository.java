package com.token.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.token.entities.User;

public interface UserRepository extends JpaRepository<User, Serializable> {

}
