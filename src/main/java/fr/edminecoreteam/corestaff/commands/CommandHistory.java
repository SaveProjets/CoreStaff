package fr.edminecoreteam.corestaff.commands;

import fr.edminecoreteam.api.account.AccountInfo;
import fr.edminecoreteam.corestaff.account.kick.KickInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHistory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AccountInfo accountInfo = new AccountInfo(args[0]);
        String uuidPlayer = accountInfo.getUUID();
        KickInfo kickInfo = new KickInfo(uuidPlayer);
        for(String test : kickInfo.getKick()){
            sender.sendMessage(test);
        }
        return false;
    }
}
