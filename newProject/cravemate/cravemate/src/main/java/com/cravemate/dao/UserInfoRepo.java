package com.cravemate.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cravemate.pojos.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    public Optional<UserInfo> findByPhonenumber(String phonenumber);
}
