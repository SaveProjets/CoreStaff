package fr.edminecoreteam.corestaff.commands;


import fr.edminecoreteam.corestaff.Core;
import fr.edminecoreteam.corestaff.utils.PlayerManager;
import fr.edminecoreteam.corestaff.utils.VanishItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

//TEST DE L'UTILISATION DE L'API
       // MenuManager test = EdmineAPI.getMenuManager();
       /* Menu inventory = EdmineAPI.getMenuManager().createMenu("test");
        Button testB = EdmineAPI.getItemManager().createItem(Material.DIAMOND_SWORD).asButton();

        testB.setButtonAction(new ButtonAction() {
            @Override
            public void onClick(Player player) {
                player.sendMessage("test");
            }
        });


        inventory.setButton("test", testB);


        UUIDInfo test = new UUIDInfo("ZayKox_");
        Bukkit.broadcastMessage(test.getUUID());*/

      //  Menu inventory = new Menu("test");

        /*Button item = new Item(Material.DIAMOND_SWORD, 13).setName("testButton").addEnchant(Enchantment.KNOCKBACK, 2).setUnbreakable(true).asButton();
        item.setButtonAction(new ButtonAction() {
            @Override
            public void onLeftClick(Player player) {
                player.sendMessage("Je suis un test !");
            }
        });
        inventory.addLine(Arrays.asList("bouton1", "", "", "", "", "", "", "", ""));


        inventory.setButton("bouton1", item);

        inventory.open((Player) sender);*/

        if (sender instanceof Player){
            Player p = (Player)sender;
            if(cmd.getName().equals("vanish")){
                if(PlayerManager.hasPermission(p.getName(), 13)){
                    if(PlayerManager.isVanished(p)){
                        PlayerManager pm = new PlayerManager(p);
                        pm.setVanished(false);
                        p.sendMessage(Core.getInstance().staffPrefix + "§cVous êtes désormais visible !");
                        if(Core.getInstance().modList.contains(p.getUniqueId())){
                            p.getInventory().setItem(7, VanishItemManager.vanishEnabled());
                        }
                    }else{
                        PlayerManager pm = new PlayerManager(p);
                        pm.setVanished(true);
                        p.sendMessage(Core.getInstance().staffPrefix + "§aVous êtes désormais invisible !");
                        if(Core.getInstance().modList.contains(p.getUniqueId())){
                            p.getInventory().setItem(7, VanishItemManager.vanishDisabled());
                        }
                    }
                }else{
                    p.sendMessage("§cCommande inconnue...");
                }
            }
        }
        return false;
    }
}
