package fr.edminecoreteam.corestaff.commands;

import fr.edminecoreteam.corestaff.account.kick.KickInfo;
import fr.edminecoreteam.corestaff.account.uuid.UUIDInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHistory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        UUIDInfo uuidInfo = new UUIDInfo(args[0]);
        String uuidPlayer = uuidInfo.getUUID();
        KickInfo kickInfo = new KickInfo(uuidPlayer);
        for(String test : kickInfo.getKick()){
            sender.sendMessage(test);
        }
        return false;
    }
}
