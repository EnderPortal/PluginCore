package fr.nkri.core.users.services;

import com.google.gson.Gson;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.auth.AuthManager;
import fr.nkri.core.users.User;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserServiceImpl implements IUserService {

    private final String API_URL = "http://localhost:3000/users";
    private final Gson GSON = new Gson();

    /**
     * Allows you to retrieve a user's information
     *
     * @param playerUuid player UUID
     * @return CompletableFuture containing the User if successful
     * or null/exception if failure.
     */
    @Override
    public CompletableFuture<User> getUser(final UUID playerUuid) {
        return CompletableFuture.supplyAsync(() -> {
            try{
                final String response = APIClient.get(API_URL + "/user", AuthManager.getINSTANCE().getTokenMap().get(playerUuid));
                return GSON.fromJson(response, User.class);
            }
            catch (final Exception e){
                e.printStackTrace();
                return null;
            }
        });
    }
}
