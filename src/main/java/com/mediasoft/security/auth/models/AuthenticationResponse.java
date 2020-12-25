package com.mediasoft.security.auth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
}
