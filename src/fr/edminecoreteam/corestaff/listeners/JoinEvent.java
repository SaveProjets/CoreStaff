package fr.edminecoreteam.corestaff.listeners;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!PlayerManager.hasPermission(e.getPlayer().getName(), 13)){
            for (UUID players : Core.getInstance().vanishList) {
                p.hidePlayer(Bukkit.getPlayer(players));
            }
        }
    }
}
