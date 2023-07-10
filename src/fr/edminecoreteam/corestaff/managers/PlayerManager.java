package fr.edminecoreteam.corestaff.managers;

import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.account.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerManager {
    private Player p;
    private ItemStack[] items = new ItemStack[40];
    private boolean vanished;

    public PlayerManager(Player p){
        this.p = p;
        vanished = false;
    }

    public static boolean hasPermission(Player p){
        RankInfo rankInfo = new RankInfo(p);
        if(rankInfo.getRankModule() >= 13){
            return true;
        }
        p.sendMessage("§cErreur, vous n'avez pas la permission...");
        return false;
    }

    public void init(){
        Core.getInstance().modList.add(p.getUniqueId());
        p.sendMessage("§aVous êtes désormais en mode Modérateur !");
        saveInventory();
        p.setGameMode(GameMode.SURVIVAL);
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(20);
        p.setFoodLevel(20);

        ItemStack invsee = new ItemStack(Material.PAPER, 1);
        ItemMeta invseeM = invsee.getItemMeta();
        invseeM.setDisplayName("§eOuvrir l'inventaire");
        invseeM.setLore(Arrays.asList("§7Faites un clique droit", "§7sur un §cjoueur", "§7pour afficher son inventaire."));
        invsee.setItemMeta(invseeM);

        ItemStack vanish = new ItemStack(Material.SULPHUR, 1);
        ItemMeta vanishM = vanish.getItemMeta();
        vanishM.setDisplayName("§2Vanish");
        vanishM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour §aActiver§7/§cDésactiver", "§7votre vanish."));
        vanish.setItemMeta(vanishM);

        ItemStack freeze = new ItemStack(Material.ICE, 1);
        ItemMeta freezeM = freeze.getItemMeta();
        freezeM.setDisplayName("§b§lFreeze");
        freezeM.setLore(Arrays.asList("§7Faites un clique droit", "§7sur un §cjoueur", "§7pour pouvoir le §c§lFREEZE."));
        freeze.setItemMeta(freezeM);

        ItemStack knockback = new ItemStack(Material.STICK, 1);
        ItemMeta knockbackM = knockback.getItemMeta();
        knockbackM.setDisplayName("§dBâton KB");
        knockbackM.setLore(Arrays.asList("§7Faites un clique gauche", "§7sur un §cjoueur", "§7pour pouvoir tester son KB."));
        knockbackM.addEnchant(Enchantment.KNOCKBACK, 10, true);
        knockbackM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        knockback.setItemMeta(knockbackM);

        ItemStack tpRandom = new ItemStack(Material.WATCH, 1);
        ItemMeta tpRandomM = tpRandom.getItemMeta();
        tpRandomM.setDisplayName("§aTéléportation Aléatoire");
        tpRandomM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour être téléporté", "§7sur un joueur §c§lALEATOIRE."));
        tpRandom.setItemMeta(tpRandomM);

        ItemStack gamemode = new ItemStack(Material.FIREWORK_CHARGE, 1);
        ItemMeta gamemodeM = gamemode.getItemMeta();
        gamemodeM.setDisplayName("§6Changer de Gamemode");
        gamemodeM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour changer votre GameMode", "§7entre Survival et Spectateur."));
        gamemode.setItemMeta(gamemodeM);

        ItemStack speed = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
        ItemMeta speedM = speed.getItemMeta();
        speedM.setDisplayName("§cChanger la vitesse de déplacement / Fly");
        speedM.setLore(Arrays.asList("§7Faites un clique droit pour", "§7ouvrir un menu de gestion de", "§7votre vitesse de déplacement / de Fly."));
        speed.setItemMeta(speedM);

        ItemStack infoPlayer = new ItemStack(Material.SUGAR_CANE, 1);
        ItemMeta infoPlayerM = speed.getItemMeta();
        infoPlayerM.setDisplayName("§cAfficher les informations du joueur.");
        infoPlayerM.setLore(Arrays.asList("§7Faites un clique droit sur un joueur", "§7pour avoir des informations concernant", "§7celui-ci."));
        infoPlayer.setItemMeta(infoPlayerM);

        p.getInventory().setItem(0, tpRandom);
        p.getInventory().setItem(1, knockback);
        p.getInventory().setItem(2, freeze);
        p.getInventory().setItem(3, invsee);
        p.getInventory().setItem(4, vanish);
        p.getInventory().setItem(6, speed);
        p.getInventory().setItem(8, gamemode);
        p.getInventory().setItem(5, infoPlayer);

        Core.getInstance().players.put(p.getUniqueId(), this);

        setVanished(true);
        for(Player players : Bukkit.getOnlinePlayers()){
            if(!hasPermission(p)){
               // players.sendMessage(Core.getInstance().getConfig().getString("leave").replaceAll("&", "§").replaceAll("%player%", p.getName()));
            }else{
                players.sendMessage("§7Le modérateur " + p.getName() + " vient d'activer son §b/mod §7!");
            }
        }
    }

    public void removeP(){
        Core.getInstance().players.remove(p.getUniqueId());
        Core.getInstance().modList.remove(p.getUniqueId());
        p.sendMessage("§cVous n'êtes désormais plu en mode modérateur !");
        p.getInventory().clear();
        giveInventory();
        p.setFlySpeed(0.1F);
        p.setWalkSpeed(0.2F);
        p.setFlying(false);
        p.setAllowFlight(false);
        setVanished(false);
        for(Player players : Bukkit.getOnlinePlayers()){
            if(!hasPermission(p)){
               // players.sendMessage(Core.getInstance().getConfig().getString("join").replaceAll("&", "§").replaceAll("%player%", p.getName()));
            }else{
                players.sendMessage("§7Le modérateur " + p.getName() + " vient de désactiver son §c/mod §7!");
            }
        }
    }

    public static PlayerManager getFromPlayer(Player p){
        return Core.getInstance().players.get(p.getUniqueId());
    }

    public static boolean isInModerationMod(Player p){
        return Core.getInstance().modList.contains(p.getUniqueId());
    }

    public ItemStack[] getItems() {
        return items;
    }

    public boolean isVanished() {
        return vanished;
    }

    private void setVanished(boolean vanished){
        this.vanished = vanished;
        if(vanished){
            for(Player players : Bukkit.getOnlinePlayers()){
                if(!hasPermission(p)){
                    players.hidePlayer(p);
                }
            }
           /* Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(p));*/
        }else{
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab player " + p.getName() + " tabprefix");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab player " + p.getName() + " customtabname");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab player " + p.getName() + " customtagname");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab player " + p.getName() + " tagprefix");
            Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(p));
        }
    }

    private void saveInventory(){
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = p.getInventory().getItem(slot);
            if(item != null){
                items[slot] = item;
            }
        }
        items[36] = p.getInventory().getHelmet();
        items[37] = p.getInventory().getChestplate();
        items[38] = p.getInventory().getLeggings();
        items[39] = p.getInventory().getBoots();


        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
    }

    private void giveInventory(){
        p.getInventory().clear();
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = items[slot];
            if(item != null){
                p.getInventory().setItem(slot, item);
            }
        }

        p.getInventory().setHelmet(items[36]);
        p.getInventory().setChestplate(items[37]);
        p.getInventory().setLeggings(items[38]);
        p.getInventory().setBoots(items[39]);

    }

}
