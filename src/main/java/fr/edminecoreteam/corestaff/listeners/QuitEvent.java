package fr.edminecoreteam.corestaff.listeners;

import fr.edminecoreteam.corestaff.utils.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(PlayerManager.isInModerationMod(p)){
            PlayerManager.getFromPlayer(p).removeP();
        }
    }
}
