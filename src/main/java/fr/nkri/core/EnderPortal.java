package fr.nkri.core;

import fr.nkri.core.auth.AuthManager;
import fr.nkri.core.auth.cmds.LoginCommand;
import fr.nkri.core.users.UserManager;
import fr.nkri.japi.JAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

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
    }

    @Override
    public void onDisable() {
    }
}
