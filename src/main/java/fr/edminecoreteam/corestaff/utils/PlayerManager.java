package fr.edminecoreteam.corestaff.utils;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.account.account.AccountInfo;
import fr.edminecoreteam.corestaff.listeners.PluginMessage.ReceivedPluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PlayerManager {
    private Player p;
    private ItemStack[] items = new ItemStack[40];
    private boolean vanished;

    private static Core core = Core.getInstance();



    public PlayerManager(Player p){
        this.p = p;
        vanished = false;
    }

    public static boolean hasPermission(String p, int permission){
        AccountInfo accountInfo = new AccountInfo(p);
        if(accountInfo.getRankModule() >= permission){
            return true;
        }
        return false;
    }

    public void init(){
        Core.getInstance().modList.add(p.getUniqueId());
        saveInventory();
        p.setGameMode(GameMode.ADVENTURE);
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(20);
        p.setFoodLevel(20);

        
        ItemStack invsee = new ItemBuilder(Material.PAPER)
                .setName("§e§lOuvrir l'inventaire §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique droit", "§7sur un §cjoueur", "§7pour afficher son inventaire.")).toItemStack();

        ItemStack freeze = new ItemBuilder(Material.ICE)
                .setName("§b§lFreeze §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique droit", "§7sur un §cjoueur", "§7pour pouvoir le §c§lFREEZE.")).toItemStack();

        ItemStack knockback = new ItemBuilder(Material.STICK)
                .setName("§d§lBâton KB §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique gauche", "§7sur un §cjoueur", "§7pour pouvoir tester son KB."))
                .addEnchant(Enchantment.KNOCKBACK, 10).hideItemFlags().toItemStack();

        ItemStack tpRandom = new ItemBuilder(Material.COMPASS)
                .setName("§a§lTéléportation Aléatoire §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique droit", "§7pour être téléporté", "§7sur un joueur §c§lALEATOIRE.")).toItemStack();


        ItemStack modMenu = new ItemBuilder(Material.ARMOR_STAND)
                .setName("§9§lMenu de Modération §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique droit", "§7pour ouvrir le menu", "§7de Modération.")).toItemStack();

        ItemStack tpPlayer = new ItemBuilder(Material.EYE_OF_ENDER)
                .setName("§a§lSe téléporter au joueur §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique droit", "§7pour vous téléporter au joueur", "§7ciblé.")).toItemStack();

        ItemStack infoPlayer = new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§c§lAfficher les informations du joueur §7• Clique")
                .setLore(Arrays.asList("§7Faites un clique droit sur un joueur", "§7pour avoir des informations concernant", "§7celui-ci.")).toItemStack();

       /* ItemStack invsee = new ItemStack(Material.PAPER, 1);
        ItemMeta invseeM = invsee.getItemMeta();
        invseeM.setDisplayName("§e§lOuvrir l'inventaire §7• Clique");
        invseeM.setLore(Arrays.asList("§7Faites un clique droit", "§7sur un §cjoueur", "§7pour afficher son inventaire."));
        invsee.setItemMeta(invseeM);

        ItemStack freeze = new ItemStack(Material.ICE, 1);
        ItemMeta freezeM = freeze.getItemMeta();
        freezeM.setDisplayName("§b§lFreeze §7• Clique");
        freezeM.setLore(Arrays.asList("§7Faites un clique droit", "§7sur un §cjoueur", "§7pour pouvoir le §c§lFREEZE."));
        freeze.setItemMeta(freezeM);

        ItemStack knockback = new ItemStack(Material.STICK, 1);
        ItemMeta knockbackM = knockback.getItemMeta();
        knockbackM.setDisplayName("§d§lBâton KB §7• Clique");
        knockbackM.setLore(Arrays.asList("§7Faites un clique gauche", "§7sur un §cjoueur", "§7pour pouvoir tester son KB."));
        knockbackM.addEnchant(Enchantment.KNOCKBACK, 10, true);
        knockbackM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        knockback.setItemMeta(knockbackM);

        ItemStack tpRandom = new ItemStack(Material.COMPASS, 1);
        ItemMeta tpRandomM = tpRandom.getItemMeta();
        tpRandomM.setDisplayName("§a§lTéléportation Aléatoire §7• Clique");
        tpRandomM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour être téléporté", "§7sur un joueur §c§lALEATOIRE."));
        tpRandom.setItemMeta(tpRandomM);

        ItemStack gamemode = new ItemStack(Material.FIREWORK_CHARGE, 1);
        ItemMeta gamemodeM = gamemode.getItemMeta();
        gamemodeM.setDisplayName("§6§lChanger de Gamemode §7• Clique");
        gamemodeM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour changer votre GameMode", "§7entre Adventure et Spectateur."));
        gamemode.setItemMeta(gamemodeM);

        ItemStack speed = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
        ItemMeta speedM = speed.getItemMeta();
        speedM.setDisplayName("§c§lChanger la vitesse de déplacement / Fly §7• Clique");
        speedM.setLore(Arrays.asList("§7Faites un clique droit pour", "§7ouvrir un menu de gestion de", "§7votre vitesse de déplacement / de Fly."));
        speed.setItemMeta(speedM);

        ItemStack infoPlayer = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta infoPlayerM = speed.getItemMeta();
        infoPlayerM.setDisplayName("§c§lAfficher les informations du joueur §7• Clique");
        infoPlayerM.setLore(Arrays.asList("§7Faites un clique droit sur un joueur", "§7pour avoir des informations concernant", "§7celui-ci."));
        infoPlayer.setItemMeta(infoPlayerM);

        ItemStack tpPlayer = new ItemStack(Material.EYE_OF_ENDER, 1);
        ItemMeta tpPlayerM = tpPlayer.getItemMeta();
        tpPlayerM.setDisplayName("§a§lSe téléporter au joueur §7• Clique");
        tpPlayerM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour vous téléporter au joueur", "§7ciblé."));
        tpPlayer.setItemMeta(tpPlayerM);

        ItemStack modMenu = new ItemStack(Material.ARMOR_STAND, 1);
        ItemMeta modMenuM = modMenu.getItemMeta();
        modMenuM.setDisplayName("§9§lMenu de Modération §7• Clique");
        modMenuM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour ouvrir le menu", "§7de Modération."));
        modMenu.setItemMeta(modMenuM);*/


        p.getInventory().setItem(0, tpRandom);
        p.getInventory().setItem(1, infoPlayer);
        p.getInventory().setItem(2, invsee);
        p.getInventory().setItem(3, tpPlayer);
        p.getInventory().setItem(4, knockback);
        p.getInventory().setItem(5, freeze);
      //  p.getInventory().setItem(6, speed);
        p.getInventory().setItem(7, VanishItemManager.vanishDisabled());
        p.getInventory().setItem(8, modMenu);
        // p.getInventory().setItem(8, gamemode);


        Core.getInstance().players.put(p.getUniqueId(), this);

        setVanished(true);

    }

    public void removeP(){
        Core.getInstance().players.remove(p.getUniqueId());
        Core.getInstance().modList.remove(p.getUniqueId());
        p.getInventory().clear();
        giveInventory();
        p.setFlySpeed(0.1F);
        p.setWalkSpeed(0.2F);
        p.setFlying(false);
        p.setAllowFlight(false);
        setVanished(false);
    }

    public static PlayerManager getFromPlayer(Player p){
        return Core.getInstance().players.get(p.getUniqueId());
    }

    public static boolean isInModerationMod(Player p){
        return Core.getInstance().modList.contains(p.getUniqueId());
    }

    public static boolean isVanished(Player p){
        return Core.getInstance().vanishList.contains(p.getUniqueId());
    }

    public ItemStack[] getItems() {
        return items;
    }

    /*public boolean isVanished() {
        return vanished;
    }*/

    public void setVanished(boolean vanished){
        //this.vanished = vanished;
        if(vanished){
            for(Player players : Bukkit.getOnlinePlayers()){
                if(!ReceivedPluginMessage.staffList.contains(players.getUniqueId())){
                    players.hidePlayer(p);
                }
            }
            if(!Core.getInstance().vanishList.contains(p.getUniqueId())){
                Core.getInstance().vanishList.add(p.getUniqueId());
                p.performCommand("edcosmetics remove");
            }
           /* Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(p));*/
        }else{
            Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(p));
            if(Core.getInstance().vanishList.contains(p.getUniqueId())) {
                Core.getInstance().vanishList.remove(p.getUniqueId());
            }
        }
    }

    public static void setFreezed(Player sender, Player target){
        if(Core.getInstance().freezedPlayers.containsKey(target.getUniqueId())){
            Core.getInstance().freezedPlayers.remove(target.getUniqueId());
            target.sendMessage("§7Vous avez été unfreeze par §c" + sender.getName() + "§7 !");
            sender.sendMessage(Core.getInstance().staffPrefix + "§aVous avez unfreeze §b" + target.getName() + " §a!");
        }else{
            Core.getInstance().freezedPlayers.put(target.getUniqueId(), target.getLocation());
            target.sendMessage("§c§lVous êtes freeze");
            target.sendMessage("§7Si vous vous §edéconnectez,");
            target.sendMessage("§7vous serez automatiquement");
            target.sendMessage("§csanctionné§7.");
            sender.sendMessage(Core.getInstance().staffPrefix + "§aVous avez freeze §b" + target.getName() + " §a!");
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
