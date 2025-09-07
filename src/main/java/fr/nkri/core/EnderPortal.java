package fr.nkri.core;

import com.google.gson.Gson;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.auth.AuthManager;
import fr.nkri.core.auth.cmds.LoginCommand;
import fr.nkri.core.users.User;
import fr.nkri.core.users.UserManager;
import fr.nkri.japi.JAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class EnderPortal extends JavaPlugin {

    @Getter
    private static EnderPortal INSTANCE;

    private AuthManager authManager;
    private UserManager userManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //init managers
        this.authManager = new AuthManager();
        this.userManager = new UserManager();

        //register commands
        JAPI.getInstance().registerCommand(new LoginCommand());

        /**
         * TEST : CALL API -> /hello, protected route by API KEY
         */
        //TODO : changer l'API KEY, mettre dans un .yml qui ne sera pas push sur GITHUB
        final String apiKey = "7E!HT$pmAT9o8kQfqfpJPFzPfA6mMpHrjYCSn&4P";

        try {
            final String response = APIClient.get("http://localhost:3000/hello", apiKey);
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
    }
}
