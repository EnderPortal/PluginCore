package fr.nkri.core;

import fr.nkri.core.auth.AuthManager;
import fr.nkri.core.auth.cmds.LoginCommand;
import fr.nkri.japi.JAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderPortal extends JavaPlugin {

    @Getter
    private static EnderPortal INSTANCE;

    private AuthManager authManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //init managers
        this.authManager = new AuthManager();

        //register commands
        JAPI.getInstance().registerCommand(new LoginCommand());
    }

    @Override
    public void onDisable() {
    }
}
