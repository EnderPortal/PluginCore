package fr.nkri.core.auth;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class AuthManager {

    @Getter
    private static AuthManager INSTANCE;

    private final IAuthService authService;

    /**
     * Stock tokens : Token map
     * @key : player Unique ID
     * @value : access token from API
     */
    private final Map<UUID, String> tokenMap;

    public AuthManager() {
        INSTANCE = this;
        this.authService = new AuthServiceImpl();
        this.tokenMap = new HashMap<>();
    }
}
