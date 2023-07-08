package fr.edminecoreteam.corestaff.commands;

import fr.edminecoreteam.corestaff.account.RankInfo;
import fr.edminecoreteam.corestaff.managers.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMod implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player)sender;
            if(PlayerManager.hasPermission(p)){
                if(cmd.getName().equals("moderation")){
                    if(PlayerManager.isInModerationMod(p)){
                        PlayerManager pm = PlayerManager.getFromPlayer(p);
                        pm.removeP();
                    }else{
                        PlayerManager pm = new PlayerManager(p);
                        pm.init();
                    }
                }
            }
        }
        return false;
    }
}
