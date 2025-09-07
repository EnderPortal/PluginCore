package fr.nkri.core;

import com.google.gson.Gson;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.auth.AuthManager;
import fr.nkri.core.auth.cmds.LoginCommand;
import fr.nkri.core.servers.ServerManager;
import fr.nkri.core.users.UserManager;
import fr.nkri.japi.JAPI;
import fr.nkri.japi.utils.configs.ConfigFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class EnderPortal extends JavaPlugin {

    @Getter
    private static EnderPortal INSTANCE;

    private AuthManager authManager;
    private UserManager userManager;
    private ServerManager serverManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //init managers
        this.authManager = new AuthManager();
        this.userManager = new UserManager();
        this.serverManager = new ServerManager(this);

        //register commands
        JAPI.getInstance().registerCommand(new LoginCommand());
    }

    @Override
    public void onDisable() {
    }
}
