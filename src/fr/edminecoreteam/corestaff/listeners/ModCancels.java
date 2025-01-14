package fr.edminecoreteam.corestaff.listeners;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ModCancels implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        e.setCancelled(PlayerManager.isInModerationMod(e.getPlayer()) || Core.getInstance().isFreeze(e.getPlayer()));
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(PlayerManager.isInModerationMod(e.getPlayer()) || Core.getInstance().isFreeze(e.getPlayer()));
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(PlayerManager.isInModerationMod(e.getPlayer()) || Core.getInstance().isFreeze(e.getPlayer()));
    }
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        e.setCancelled(PlayerManager.isInModerationMod(e.getPlayer()) || Core.getInstance().isFreeze(e.getPlayer()));
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player))return;
        e.setCancelled(PlayerManager.isInModerationMod((Player) e.getEntity()) || Core.getInstance().isFreeze((Player) e.getEntity()));
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        e.setCancelled(PlayerManager.isInModerationMod(e.getPlayer()) || Core.getInstance().isFreeze(e.getPlayer()));
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        e.setCancelled(PlayerManager.isInModerationMod((Player) e.getWhoClicked()) || Core.getInstance().isFreeze((Player) e.getWhoClicked()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryOpen(InventoryOpenEvent e){
        e.setCancelled(Core.getInstance().isFreeze((Player) e.getPlayer()));

    }
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player))return;
        if(!(e.getDamager() instanceof Player))return;

        Player damager = (Player) e.getDamager();
        if(PlayerManager.isInModerationMod(damager)){
            e.setCancelled(damager.getInventory().getItemInHand().getType() != Material.STICK);
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(Core.getInstance().isFreeze(e.getPlayer())) {
            e.setTo(e.getFrom());
        }
    }

    @EventHandler
    public void onBouffe(FoodLevelChangeEvent e){
        e.setCancelled(PlayerManager.isInModerationMod((Player) e.getEntity()) || Core.getInstance().isFreeze((Player) e.getEntity()));
    }
}
