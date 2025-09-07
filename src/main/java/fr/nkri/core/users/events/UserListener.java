package fr.nkri.core.users.events;

import fr.nkri.core.users.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

    private final UserManager userManager;
    public UserListener(final UserManager userManager){
        this.userManager = userManager;
    }

    //Remove user account, if player leave server
    @EventHandler
    public void onQuit(final PlayerQuitEvent e){
        this.userManager.removeUser(e.getPlayer().getUniqueId());
    }
}
