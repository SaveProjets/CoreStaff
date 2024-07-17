package fr.edminecoreteam.corestaff.utils;


import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class VanishItemManager {
    private static ItemBuilder itB = new ItemBuilder(Material.INK_SACK, 1);
/*
    public static ItemStack vanishDisabled(){
        ItemStack vanishDisabled = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta vanishDM = vanishDisabled.getItemMeta();
        vanishDM.setDisplayName("§a§lVanish §7• Clique");
        vanishDM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour §cDésactiver", "§7votre vanish."));
        vanishDisabled.setItemMeta(vanishDM);

        return vanishDisabled;
    }

    public static ItemStack vanishEnabled(){
        ItemStack vanishEnabled = new ItemStack(Material.INK_SACK, 1, (short) 8);
        ItemMeta vanishEM = vanishEnabled.getItemMeta();
        vanishEM.setDisplayName("§c§lVanish §7• Clique");
        vanishEM.setLore(Arrays.asList("§7Faites un clique droit", "§7pour §aActiver", "§7votre vanish."));
        vanishEnabled.setItemMeta(vanishEM);

        return vanishEnabled;
    }
*/

    public static ItemStack vanishDisabled(){
        ItemStack vanishDisabled = itB
                .setName("§a§lVanish §7• Clique")
                .setDurability((short) 10)
                .setLore(Arrays.asList("§7Faites un clique droit", "§7pour §cDésactiver", "§7votre vanish.")).toItemStack();

        return vanishDisabled;
    }

    public static ItemStack vanishEnabled(){
        ItemStack vanishEnabled = itB
                .setName("§c§lVanish §7• Clique")
                .setDurability((short) 8)
                .setLore(Arrays.asList("§7Faites un clique droit", "§7pour §cDésactiver", "§7votre vanish.")).toItemStack();

        return vanishEnabled;
    }
}
