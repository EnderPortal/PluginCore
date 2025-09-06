package fr.nkri.core.auth.cmds;

import fr.nkri.core.EnderPortal;
import fr.nkri.core.auth.AuthManager;
import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.japi.cmds.ICommand;
import fr.nkri.japi.cmds.interfaces.Command;
import fr.nkri.japi.utils.JUtils;
import org.bukkit.Bukkit;

public class LoginCommand extends ICommand {

    private final AuthManager authManager = AuthManager.getINSTANCE();

    @Override
    @Command(name = "login")
    public boolean onCommand(final CommandArguments args) {
        final String username = args.getPlayer().getName();
        final String password = args.getArgs(0);

        /**
         * Check if password is not null
         */
        if(password.isEmpty()){
            args.getPlayer().sendMessage(JUtils.color("&cVeuillez entrer un mot de passe pour continuer."));
            return false;
        }

        //Call async method
        authManager.getAuthService().login(username, password).thenAccept(result -> {

            //I'm coming back to the main Minecraft thread
            Bukkit.getScheduler().runTask(EnderPortal.getINSTANCE(), () -> {
                if(result == null){
                    args.getPlayer().sendMessage(JUtils.color("&cEchec de connexion à l'API"));
                    return;
                }

                final String token = result.getToken();
                args.getPlayer().sendMessage(JUtils.color("&aAuthentification réussie !"));
            });
        });

        return false;
    }
}
