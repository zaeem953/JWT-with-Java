package com.sms.jwt.repository;

import com.sms.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    public Optional<User> findByEmail(String email);
}
