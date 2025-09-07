package fr.nkri.core.auth.cmds;

import fr.nkri.core.EnderPortal;
import fr.nkri.core.api.APIClient;
import fr.nkri.core.auth.AuthManager;
import fr.nkri.core.users.UserManager;
import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.japi.cmds.ICommand;
import fr.nkri.japi.cmds.interfaces.Command;
import fr.nkri.japi.utils.JUtils;
import org.bukkit.Bukkit;

import java.io.IOException;

public class LoginCommand extends ICommand {

    private final AuthManager authManager = AuthManager.getINSTANCE();
    private final UserManager userManager = UserManager.getINSTANCE();

    @Override
    @Command(name = "login")
    public boolean onCommand(final CommandArguments args) {
        final String username = args.getPlayer().getName();
        final String password = args.getArgs(0);

        /**
         * Check args
         */
        if(args.length() == 0){
            args.getPlayer().sendMessage(JUtils.color("&c/login <password>"));
            return false;
        }

        /**
         * Check if password is not null
         */
        if(password.isEmpty()){
            args.getPlayer().sendMessage(JUtils.color("&cVeuillez entrer un mot de passe pour continuer."));
            return false;
        }

        args.getPlayer().sendMessage(JUtils.color("&eConnexion en cours..."));

        //Call async method
        authManager.getAuthService().login(username, password).thenAccept(result -> {

            //I'm coming back to the main Minecraft thread
            Bukkit.getScheduler().runTask(EnderPortal.getINSTANCE(), () -> {

                /*
                No response
                 */
                if(result == null){
                    args.getPlayer().sendMessage(JUtils.color("&cEchec de connexion à l'API"));
                    return;
                }

                /*
                If the token is empty or null, retrieves the API error message
                or displays 'Erreur inconue', then sends it to the player.
                 */
                if(result.getToken() == null || result.getToken().isEmpty()){
                    final String errorMessage = result.getMessage() != null ? result.getMessage() : "Erreur inconue";
                    args.getPlayer().sendMessage(JUtils.color("&cEchec d'authentification : " + errorMessage));
                    return;
                }

                final String token = result.getToken();
             //   args.getPlayer().sendMessage(JUtils.color("&aAuthentification réussie !"));

                /*
                Stock token in local
                 */
                this.authManager.addToken(args.getPlayer().getUniqueId(), token);

                /**
                 * Load an account and recovery of player game data
                 */
                this.userManager.loadUser(args.getPlayer());
            });
        });

        return false;
    }
}
