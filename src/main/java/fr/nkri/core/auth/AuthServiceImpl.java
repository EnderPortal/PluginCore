package fr.nkri.core.auth;

import com.google.gson.Gson;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.auth.dtos.AuthResponse;
import fr.nkri.core.auth.dtos.LoginRequest;

import java.util.concurrent.CompletableFuture;

public class AuthServiceImpl implements IAuthService{

    private final String API_URL = "http://localhost:3000/auth";
    private final Gson GSON = new Gson();

    /**
     * Attempt to connect a player with their
     * username and password
     *
     * @param username player name
     * @param password player password
     * @return Completable Future containing the authentication response
     * or null in case of error
     */
    @Override
    public CompletableFuture<AuthResponse> login(final String username, final String password) {
        //NOTE: I use CompletableFuture to not block the main thread of Minecraft, when I make my HTTP calls
        return CompletableFuture.supplyAsync(() -> {
            try{
                final LoginRequest loginRequestDTO = new LoginRequest(username, password);
                final String jsonBody = GSON.toJson(loginRequestDTO);
                final String response = APIClient.post(API_URL, jsonBody);

                return GSON.fromJson(response, AuthResponse.class);
            }
            catch (final Exception e){
                e.printStackTrace();
                return null;
            }
        });
    }
}
