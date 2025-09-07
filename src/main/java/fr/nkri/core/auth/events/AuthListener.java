package fr.nkri.core.auth.events;

import fr.nkri.core.auth.AuthManager;
import fr.nkri.japi.utils.JUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

public class AuthListener implements Listener {

    private final AuthManager authManager;
    public AuthListener(final AuthManager authManager) {
        this.authManager = authManager;
    }

    //Feedback player
    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        e.setJoinMessage(null);
        e.getPlayer().sendMessage(JUtils.color("&7—————————————————————————————————————————————————"));
        e.getPlayer().sendMessage(JUtils.color("&5&lBienvenue sur &d&lEnderPortal"));
        e.getPlayer().sendMessage("");
        e.getPlayer().sendMessage(JUtils.color("&ePour vous connecter, utilisez la commande :"));
        e.getPlayer().sendMessage(JUtils.color("&a/login <password>"));
        e.getPlayer().sendMessage("");
        e.getPlayer().sendMessage(JUtils.color("&7&oPas encore de compte ? Créez-en un ici : &9&ohttp://localhost:5173"));
        e.getPlayer().sendMessage(JUtils.color("&7—————————————————————————————————————————————————"));
    }

    //remove player token
    @EventHandler
    public void onQuit(final PlayerQuitEvent e){
        this.authManager.removeToken(e.getPlayer().getUniqueId());
    }

    /*
    Action cancelled, if player is not login
     */
    @EventHandler
    public void onCommand(final PlayerCommandPreprocessEvent e){
        if(!this.authManager.isAuth(e.getPlayer().getUniqueId())) {
            final String message = e.getMessage().toLowerCase();

            /*
            All commands are blocked except /login
             */
            if(!message.startsWith("/login")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(JUtils.color("&cVous devez vous connecter : §e/login <password>&c."));
            }
        }
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e){
        if(!this.authManager.isAuth(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent e){
        if(!this.authManager.isAuth(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent e){
        if(!this.authManager.isAuth(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e){
        if(!this.authManager.isAuth(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(JUtils.color("&cVous devez vous connecter : §e/login <password>&c."));
        }
    }
}
