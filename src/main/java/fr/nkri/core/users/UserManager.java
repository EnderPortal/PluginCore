package fr.nkri.core.users;

import fr.nkri.core.EnderPortal;
import fr.nkri.core.users.events.UserListener;
import fr.nkri.core.users.services.UserServiceImpl;
import fr.nkri.japi.JAPI;
import fr.nkri.japi.utils.JUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserManager {

    @Getter
    private static UserManager INSTANCE;

    private final Map<UUID, User> users;

    /**
     *
     */
    private final UserServiceImpl userService;

    public UserManager(){
        INSTANCE = this;
        this.users = new HashMap<>();
        this.userService = new UserServiceImpl();

        /**
         * Register listeners
         */
        JAPI.getInstance().registerListeners(new UserListener(this));
    }

    /**
     * Load a user in local and recovery of player game data
     * @param player player account to create
     */
    public void loadUser(final Player player){
        /**
         * Call API and create account in local
         */
        UserManager.getINSTANCE().getUserService().getUser(player.getUniqueId()).thenAccept(user -> {
            //I'm coming back to the main Minecraft thread
            Bukkit.getScheduler().runTask(EnderPortal.getINSTANCE(), () -> {
                if(user == null){
                    player.sendMessage(JUtils.color("&cErreur : impossible de récupérer vos données."));
                    return;
                }

                this.users.putIfAbsent(player.getUniqueId(), user);

                player.sendMessage(JUtils.color("&aAuthentification réussie, données chargées !").replace("%name%", player.getName()));
                player.sendMessage("");

                /**
                 * Message temporaire :)
                 */
                player.sendMessage(JUtils.color("&7—————————————————————————————————————————————————"));
                player.sendMessage(JUtils.color("&5&lBienvenue &e%name% &5sur &d&lEnderPortal").replace("%name%", user.getUsername()));
                player.sendMessage(JUtils.color("&6Vos statistiques:"));
                player.sendMessage(JUtils.color("&eCoins : &a%coins%".replace("%coins%", String.valueOf(user.getProfile().getCoins()))));
                player.sendMessage(JUtils.color("&7—————————————————————————————————————————————————"));
            });
        });
    }

    /**
     * Remove a player, in local data
     * @param playerUUID player Unique ID
     */
    public void removeUser(final UUID playerUUID){
        this.users.remove(playerUUID);
    }
}
