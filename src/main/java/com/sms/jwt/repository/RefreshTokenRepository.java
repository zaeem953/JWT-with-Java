package com.sms.jwt.repository;

import com.sms.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    //Custom
    Optional<RefreshToken> findByRefreshToken(String token);
}
