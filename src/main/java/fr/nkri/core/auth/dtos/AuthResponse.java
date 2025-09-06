package fr.nkri.core.auth.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class AuthResponse {

    @SerializedName("access_token")
    private final String token;

    private final String message;

    /**
     * Data received from the API after login
     * @param token player token
     */
    public AuthResponse(final String token, final String message) {
        this.token = token;
        this.message = message;
    }
}
