package fr.edminecoreteam.corestaff.listeners;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import fr.edminecoreteam.corestaff.utils.VanishItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ModInteractItems implements Listener {
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        if(!PlayerManager.isInModerationMod(p)) return;
        if(!(e.getRightClicked() instanceof Player)) return;
        Player t = (Player) e.getRightClicked();
        switch (p.getInventory().getItemInHand().getType()){
            case ICE:
                PlayerManager.setFreezed(p, t);
                break;

            case PAPER:
                Inventory inv = Bukkit.createInventory(null, 5*9, "Inventaire de §b" + t.getName());

                for (int i = 0; i < 36; i++) {
                    if (t.getInventory().getItem(i) != null) {
                        inv.setItem(i, t.getInventory().getItem(i));
                    }
                }
                inv.setItem(36, t.getInventory().getHelmet());
                inv.setItem(37, t.getInventory().getChestplate());
                inv.setItem(38, t.getInventory().getLeggings());
                inv.setItem(39, t.getInventory().getBoots());

                p.openInventory(inv);

                break;

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!PlayerManager.isInModerationMod(p)) return;
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        switch (p.getInventory().getItemInHand().getType()){

            case COMPASS:
                List<Player> PlayerList = new ArrayList<>(Bukkit.getOnlinePlayers());
                PlayerList.remove(p);

                Iterator<Player> PlayerListIt = PlayerList.iterator();
                while (PlayerListIt.hasNext()){
                    Player player = PlayerListIt.next();
                    if(PlayerManager.hasPermission(player.getName(), 13)){
                        PlayerListIt.remove();
                    }
                }

               /* for(Player players : PlayerList){
                    if(PlayerManager.hasPermission(players)) {
                        PlayerList.remove(players);
                    }
                }*/

                if(PlayerList.size() == 0){
                    p.sendMessage(Core.getInstance().staffPrefix + "§cIl n'y a aucun joueur sur lequel se téléporter...");
                    return;
                }else{
                    Player target = PlayerList.get(new Random().nextInt(PlayerList.size()));
                    p.teleport(target.getLocation());
                    p.sendMessage(Core.getInstance().staffPrefix + "§aVous avez été téléporté aléatoirement à §b" + target.getName());
                }
                break;

            case INK_SACK:
                if(PlayerManager.isVanished(p)){
                    PlayerManager pm = new PlayerManager(p);
                    pm.setVanished(false);
                    p.getInventory().setItem(7, VanishItemManager.vanishEnabled());
                    p.sendMessage(Core.getInstance().staffPrefix + "§cVous êtes désormais visible !");
                }else if(!PlayerManager.isVanished(p)){
                    PlayerManager pm = new PlayerManager(p);
                    pm.setVanished(true);
                    p.getInventory().setItem(7, VanishItemManager.vanishDisabled());
                    p.sendMessage(Core.getInstance().staffPrefix + "§aVous êtes désormais invisible !");

                }

        }
    }
}
