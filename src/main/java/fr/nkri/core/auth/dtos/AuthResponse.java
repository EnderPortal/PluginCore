package fr.nkri.core.auth.dtos;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final String token, message;

    /**
     * Data received from the API after login
     * @param token player token
     */
    public AuthResponse(final String token, final String message) {
        this.token = token;
        this.message = message;
    }
}
