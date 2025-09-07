package fr.nkri.core.servers;

import fr.nkri.core.EnderPortal;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.servers.ranks.Rank;
import fr.nkri.core.servers.ranks.RankManager;
import fr.nkri.core.servers.services.ServerServiceImpl;
import fr.nkri.core.users.services.UserServiceImpl;
import fr.nkri.japi.logs.Logs;
import fr.nkri.japi.logs.enums.LogsType;
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
    @Getter
    private final YamlConfiguration configEnv;

    /**
     * Register server services
     */
    private final ServerServiceImpl serverService;

    /**
     * All servers modules
     */
    @Getter
    private RankManager rankManager;

    public ServerManager(final EnderPortal main) {
        INSTANCE = this;

        //init configs
        this.configEnv = new ConfigFile(main, "env.yml").get();

        //init services
        this.serverService = new ServerServiceImpl();

        //register manager
        this.rankManager = new RankManager();

        //TEST
        loadData();
    }

    /*
    TODO : pour le test, voir si on peut faire un truc plus beau ?
     */
    private void loadData(){
        this.serverService.getAllRanks().thenAccept(ranks -> {
            if(ranks == null){
                Logs.sendLog("[ServerService]", "Error loading ranks/roles", LogsType.ERROR);
                return;
            }

            for(Rank rank : ranks){
                System.out.println(rank.getName());
                this.rankManager.addRank(rank);
            }
        });
    }
}
