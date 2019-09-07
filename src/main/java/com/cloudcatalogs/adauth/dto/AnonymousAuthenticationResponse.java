package com.cloudcatalogs.adauth.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created on 2019-04-01.
 */
@Data
@Builder
public class AnonymousAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private Boolean success;
    private String username;
    private String address;
    private UserResponse user;
}
