package com.sms.jwt.entity;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String refreshToken;
}
