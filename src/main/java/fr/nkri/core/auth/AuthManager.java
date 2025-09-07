package fr.nkri.core.auth;

import fr.nkri.core.auth.cmds.LoginCommand;
import fr.nkri.core.auth.events.AuthListener;
import fr.nkri.core.auth.services.AuthServiceImpl;
import fr.nkri.core.auth.services.IAuthService;
import fr.nkri.japi.JAPI;
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

        //register event bukkit
        JAPI.getInstance().registerListeners(new AuthListener(this));

        //register commands
        JAPI.getInstance().registerCommand(new LoginCommand());
    }

    /**
     * Removes the player from the token list
     * @param uuid player UUID
     */
    public void removeToken(final UUID uuid){
        this.tokenMap.remove(uuid);
        //TODO : logout
    }

    /**
     * Adds a player to the token list
     * @param uuid player UUID
     * @param token access_token
     */
    public void addToken(final UUID uuid, final String token){
        this.tokenMap.putIfAbsent(uuid, token);
    }

    /**
     * Check if the player is logged in
     * @param uuid player UUID
     * @return true if it is login, otherwise false
     */
    public boolean isAuth(final UUID uuid){
        return this.tokenMap.containsKey(uuid);
    }
}
