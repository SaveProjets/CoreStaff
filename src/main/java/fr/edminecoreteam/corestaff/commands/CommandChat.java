package fr.edminecoreteam.corestaff.commands;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.api.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CommandChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (PlayerManager.hasPermission(sender.getName(), 12) || sender instanceof ConsoleCommandSender) {
            if (cmd.getName().equals("chat")) {
                if (args.length == 0) {
                    help(sender);
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        help(sender);
                    } else if (args[0].equalsIgnoreCase("clear")) {
                        clear(sender);
                    } else if (args[0].equalsIgnoreCase("close")) {
                        disabledChat(sender);
                    } else if (args[0].equalsIgnoreCase("open")) {
                        enabledChat(sender);
                    }else{
                        help(sender);
                    }
                }else{
                    help(sender);
                }
            }
        } else {
            sender.sendMessage("§cCommande inconnue...");
        }
        return false;
    }


    private void help(CommandSender sender){
        sender.sendMessage("");
        sender.sendMessage(" §7» §6§lCentre d'aide §6(EDMINE CHAT):");
        sender.sendMessage("");
        sender.sendMessage(" §7• §d/§fchat help §8§l» §7Afficher l'aide.");
        sender.sendMessage(" §7• §d/§fchat clear §8§l» §7Clear le tchat du serveur actuel.");
        sender.sendMessage(" §7• §d/§fchat close §8§l» §7Fermer le tchat du serveur actuel.");
        sender.sendMessage(" §7• §d/§fchat open §8§l» §7Ouvrir le tchat du serveur actuel.");
        sender.sendMessage("");
    }

    private void clear(CommandSender sender){
        for (int i = 0; i < 100; i++){
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage("§cLe tchat du serveur a été effacé par §e" + sender.getName() + " §c!");

        sender.sendMessage(Core.getInstance().staffPrefix + "§cVous avez effacé le tchat avec succès !");
    }

    private void enabledChat(CommandSender sender){
        if (Core.getInstance().chatEnabled){
            sender.sendMessage(Core.getInstance().staffPrefix + "§cLe tchat est déjà activé !");
        }else{
            Core.getInstance().chatEnabled = true;
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§aLe tchat du serveur a été ouvert par §e" + sender.getName() + " §a!");
            Bukkit.broadcastMessage("");
            sender.sendMessage(Core.getInstance().staffPrefix + "§aLe tchat a été activé avec succès !");
        }
    }

    private void disabledChat(CommandSender sender){
        if (!Core.getInstance().chatEnabled){
            sender.sendMessage(Core.getInstance().staffPrefix + "§cLe tchat est déjà désactivé !");
        }else{
            Core.getInstance().chatEnabled = false;
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§cLe tchat du serveur a été fermé par §e" + sender.getName() + " §c!");
            Bukkit.broadcastMessage("");
            sender.sendMessage(Core.getInstance().staffPrefix + "§aLe tchat a été désactivé avec succès !");

        }
    }
}
