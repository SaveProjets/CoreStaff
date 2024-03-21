package fr.edminecoreteam.corestaff.commands;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFreeze implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (PlayerManager.hasPermission(p.getName(), 13)) {
                if (cmd.getName().equals("freeze")) {
                    if (args.length == 0) {
                        help(p);
                    } else if (args.length == 1) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t != null && t.isOnline()) {
                            PlayerManager.setFreezed(p, t);
                        }else {
                            p.sendMessage(Core.getInstance().staffPrefix + "§cLe joueur §e" + args[0] + " §cn'est pas connecté sur ce serveur...");
                        }
                    }else{
                        help(p);
                    }
                }

            } else {
                p.sendMessage("§cCommande inconnue...");
            }
        }

        return false;
    }

    private void help(CommandSender sender){
        sender.sendMessage("");
        sender.sendMessage(" §7» §6§lCentre d'aide §6(EDMINE STAFF):");
        sender.sendMessage("");
        sender.sendMessage(" §7• §d/§ffreeze [pseudo] §8§l» §7Freeze/Unfreeze un joueur.");
        sender.sendMessage("");
    }
}
