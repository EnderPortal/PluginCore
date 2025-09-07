package fr.nkri.core.users.cmds;

import fr.nkri.core.users.User;
import fr.nkri.core.users.UserManager;
import fr.nkri.japi.cmds.CommandArguments;
import fr.nkri.japi.cmds.ICommand;
import fr.nkri.japi.cmds.interfaces.Command;
import fr.nkri.japi.utils.JUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoinsCommand extends ICommand {

    private final UserManager userManager;
    public CoinsCommand(final UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    @Command(name = "coins", permissionNode = "core.coins.admin")
    public boolean onCommand(CommandArguments args) {
        final Player player = (Player) args.getPlayer();

        /**
         * Check args
         */
        if(args.length() != 3){
            sendHelp(player);
            return false;
        }

        /**
         * Check if amount is a integer number
         */
        final String amountStr = args.getArgs(1);
        if(!JUtils.isInteger(amountStr)){
            player.sendMessage(JUtils.color("&cSeuls les nombres entiers sont valides pour les pièces."));
            return false;
        }

        final int amount = Integer.parseInt(amountStr);

        /**
         * Find player, if is online
         */
        final String targetStr =  args.getArgs(2);
        if(Bukkit.getPlayer(targetStr) == null){
            player.sendMessage(JUtils.color("&cLe joueur %name% n'est pas en ligne.")
                    .replace("%name%", targetStr));
            return false;
        }

        /**
         * Check user account
         */
        final Player target = Bukkit.getPlayer(targetStr);
        final User user = this.userManager.getUsers().get(target.getUniqueId());

        if(user == null){
            player.sendMessage(JUtils.color("&cErreur : les données de %name% ne sont pas disponible en cache."));
            return false;
        }

        user.getProfile().setCoins(amount);
        player.sendMessage(JUtils.color("&eVous venez de donner &c%amount% &6coins &eà &a%target%")
                .replace("%target%", targetStr)
                .replace("%amount%", String.valueOf(amount)));

        target.sendMessage(JUtils.color("&eVotre solde de &6coins &ea été mis à jour : &6%amount%&e.")
                .replace("%amount%", String.valueOf(amount)));

        //TODO : update avec un POST / PATCH vers l'api ? -> créer la rotue !
        return true;
    }

    /**
     * Show help
     * @param player player to send help message
     */
    private void sendHelp(final Player player) {
        player.sendMessage(JUtils.LINE);
        player.sendMessage("§6§lCoins:");
        player.sendMessage("");
        player.sendMessage("§e/coins set <amount> <player> §7: §6Gère les points d'un joueur.");
        player.sendMessage(JUtils.LINE);
    }
}
