package fr.nkri.core.servers.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.servers.ServerManager;
import fr.nkri.core.servers.ranks.Rank;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ServerServiceImpl implements IServerService {

    private final String API_URL = "http://localhost:3000/server";
    private final Gson GSON = new Gson();

    /**
     * Get all grades from the API
     * @return Rank array
     */
    @Override
    public CompletableFuture<Set<Rank>> getAllRanks() {
        return CompletableFuture.supplyAsync(() -> {
            try{
                final String response = APIClient.get(API_URL + "/ranks", ServerManager.getINSTANCE().getConfigEnv().getString("env.API_KEY"));
                final Type typeAdapter = new TypeToken<Set<Rank>>() {}.getType();

                return GSON.fromJson(response, typeAdapter);
            }
            catch (final Exception e){
                e.printStackTrace();
                return null;
            }
        });
    }
}
