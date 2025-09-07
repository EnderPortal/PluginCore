package fr.nkri.core.users.services;

import fr.nkri.core.users.User;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * All methods for communicating with user data
 */
public interface IUserService {

    /**
     * Allows you to retrieve a user's information
     *
     * @param uuid player uuid
     * @return CompletableFuture containing the User if successful
     * or null/exception if failure.
     */
    CompletableFuture<User> getUser(final UUID uuid);
}
