package fr.nkri.core.auth.services;

import fr.nkri.core.auth.services.dtos.AuthResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Player authentication service, contains all
 * associated methods
 */
public interface IAuthService {

    /**
     * Attempts to log in a player with their username and password.
     *
     * @param username player name
     * @param password player password
     * @return CompletableFuture containing the AuthResponse if successful
     * or null/exception if failure.
     */
    CompletableFuture<AuthResponse> login(final String username, final String password);
}
