package com.sms.jwt.service;

import com.sms.jwt.entity.RefreshToken;
import com.sms.jwt.entity.User;
import com.sms.jwt.repository.RefreshTokenRepository;
import com.sms.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public long refreshTokenValidity=2*60*1000;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username){

        User user=userRepository.findByEmail(username).get();
        RefreshToken refreshToken=user.getRefreshToken();
        if (refreshToken==null){
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity))
                    .user(userRepository.findByEmail(username).get())
                    .build();
        }else {
            refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }


        refreshTokenRepository.save(refreshToken);
        return refreshToken;

    }

    public RefreshToken verifyRefreshToken(String refreshToken){
        RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()->new RuntimeException("Given Token does not exist in db"));
        if (refreshTokenOb.getExpiry().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshTokenOb);
            throw new RuntimeException("Inavalid Token");
        }
        return refreshTokenOb;
    }
}
