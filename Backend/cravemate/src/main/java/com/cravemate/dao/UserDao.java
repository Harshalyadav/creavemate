package com.cravemate.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cravemate.pojos.User;



public interface UserDao extends JpaRepository<User, Long>{
	Optional<User> findByEmailAndPassword(String em,String pass);
}
