package fr.nkri.core.servers;

import fr.nkri.core.EnderPortal;
import fr.nkri.core.api.APIClient;
import fr.nkri.japi.utils.configs.ConfigFile;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

/**
 * Stores the entire "general" part of the server:
 * list of ranks, etc.
 */
public class ServerManager {

    @Getter
    private static ServerManager INSTANCE;

    /**
     * Allows you to retrieve key type information,
     * which must be hidden in the github repo
     */
    private final YamlConfiguration configEnv;

    public ServerManager(final EnderPortal main) {
        INSTANCE = this;

        //init configs
        this.configEnv = new ConfigFile(main, "env.yml").get();

        /**
         * TEST : CALL API -> /hello, protected route by API KEY
         */
        final String apiKey = this.configEnv.getString("env.API_KEY");

        try {
            final String response = APIClient.get("http://localhost:3000/hello", apiKey);
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
