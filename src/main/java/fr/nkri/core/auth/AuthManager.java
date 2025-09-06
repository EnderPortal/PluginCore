package fr.nkri.core.auth;

import lombok.Getter;

public class AuthManager {

    @Getter
    private static AuthManager INSTANCE;

    @Getter
    private final IAuthService authService;

    public AuthManager() {
        INSTANCE = this;
        this.authService = new AuthServiceImpl();
    }
}
