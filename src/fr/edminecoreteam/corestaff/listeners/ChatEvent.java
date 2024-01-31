package fr.edminecoreteam.corestaff.listeners;

import fr.edminecoreteam.api.utils.PlayerManager;
import fr.edminecoreteam.corestaff.Core;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        if(!Core.getInstance().chatEnabled){
            Player p = e.getPlayer();
            if(!PlayerManager.hasPermission(p.getName(), 12)){
                p.sendMessage("§cLe tchat est actuellement désactivé.");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                e.setCancelled(true);
                e.setMessage(null);
            }
        }
    }
}
