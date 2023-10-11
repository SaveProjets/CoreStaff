package fr.edminecoreteam.corestaff.listeners.PluginMessage;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.account.account.AccountInfo;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.UUID;

public class ReceivedPluginMessage implements PluginMessageListener {
    public static ArrayList<UUID> staffList = new ArrayList<>();
    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {
        if(!channel.equals("ModActivate") && !channel.equals("ModIsActivate")){
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();

        if(subChannel.equals("ModActivate")){
            String playerName = in.readUTF();
            int staffCount = in.readInt();

            staffList.clear();
            for(int i = 0; i < staffCount; i++){
                String staffUUID = in.readUTF();
                staffList.add(UUID.fromString(staffUUID));
            }

            boolean modActivation = in.readBoolean();

            Player player = Bukkit.getPlayer(playerName);
            if(player != null && player.isOnline()){
                if(modActivation){
                    PlayerManager pm = new PlayerManager(player);
                    pm.init();
                }else if (!modActivation){
                    PlayerManager.getFromPlayer(player).removeP();
                }
            }
            AccountInfo accountInfo = new AccountInfo(playerName);

            for(UUID staff : staffList){
                Player staffP = Bukkit.getPlayer(staff);
                if(staffP != null && staffP.isOnline() && !staffP.getName().equalsIgnoreCase(playerName)){
                    if(modActivation){
                        staffP.sendMessage( Core.getInstance().staffPrefix + "§e" + playerName + "§7 vient d'activer son §b/mod §7!");
                    }else{
                        staffP.sendMessage(Core.getInstance().staffPrefix + "§e" + playerName + " §7vient de désactiver son §c/mod §7!");
                    }
                }
            }

        }

        if (subChannel.equals("ModIsActivate")){
            String playerName = in.readUTF();
            int staffCount = in.readInt();

            staffList.clear();
            for(int i = 0; i < staffCount; i++){
                 String staffUUID = in.readUTF();
                 staffList.add(UUID.fromString(staffUUID));
            }

            Player player = Bukkit.getPlayer(playerName);
            if(player != null && player.isOnline()){
                PlayerManager pm = new PlayerManager(player);
                pm.init();
            }


        }

    }
}
