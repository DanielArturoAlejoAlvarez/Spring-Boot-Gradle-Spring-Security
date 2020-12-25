package com.mediasoft.security.auth.models;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
}
