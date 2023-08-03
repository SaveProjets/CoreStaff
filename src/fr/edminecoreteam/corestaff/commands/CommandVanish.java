package fr.edminecoreteam.corestaff.commands;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import fr.edminecoreteam.corestaff.utils.VanishItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player)sender;
            if(PlayerManager.hasPermission(p.getName(), 13)){
                if(cmd.getName().equals("vanish")){
                    if(PlayerManager.isVanished(p)){
                        PlayerManager pm = new PlayerManager(p);
                        pm.setVanished(false);
                        p.sendMessage(Core.getInstance().staffPrefix + "§cVous êtes désormais visible !");
                        if(Core.getInstance().modList.contains(p.getUniqueId())){
                            p.getInventory().setItem(7, VanishItemManager.vanishEnabled());
                        }
                    }else{
                        PlayerManager pm = new PlayerManager(p);
                        pm.setVanished(true);
                        p.sendMessage(Core.getInstance().staffPrefix + "§aVous êtes désormais invisible !");
                        if(Core.getInstance().modList.contains(p.getUniqueId())){
                            p.getInventory().setItem(7, VanishItemManager.vanishDisabled());
                        }
                    }
                }
            }else{
                p.sendMessage("§cCommande inconnue...");
            }
        }
        return false;
    }
}
