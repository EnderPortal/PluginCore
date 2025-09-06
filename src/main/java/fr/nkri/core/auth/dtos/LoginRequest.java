package fr.nkri.core.auth.dtos;

public class LoginRequest {

    private final String username, password;

    /**
     * Request sent to the API containing the
     * login information
     *
     * @param username player name
     * @param password player password
     */
    public LoginRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
