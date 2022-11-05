package me.mrsam7k.bfhangman;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class InventoryMenu {

    private static final Plugin plugin = Main.getPlugin(Main.class);

    public static void newInventory(Player player, int slots, String title){
        Inventory inv = plugin.getServer().createInventory(null, slots, Main.toColor(title));
        player.openInventory(inv);
    }

    public static void modeMenu(Player player){
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2, 1);
        ItemStack single = new ItemStack(Material.IRON_NUGGET, 1);
        ItemMeta singleMeta = single.getItemMeta();
        singleMeta.setDisplayName(Main.toColor("&3Custom Word"));
        String[] singleLoreList = {"", Main.toColor("&7Choose word from a list")};
        List<String> singleLore = Arrays.asList(singleLoreList);
        singleMeta.setLore(singleLore);

        single.setItemMeta(singleMeta);

        ItemStack multi = new ItemStack(Material.PRISMARINE_CRYSTALS, 1);
        ItemMeta multiMeta = multi.getItemMeta();
        multiMeta.setDisplayName(Main.toColor("&3Random Word"));

        String[] multiLoreList = {"", Main.toColor("&7Game randomly selects word from list.")};
        List<String> multiLore = Arrays.asList(multiLoreList);
        multiMeta.setLore(multiLore);
        multi.setItemMeta(multiMeta);

        Inventory inv = plugin.getServer().createInventory(null, 27, "Select mode");
        inv.setItem(12, single);
        inv.setItem(14, multi);
        player.openInventory(inv);
    }

    public static void chooseWord(Player player){
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2 ,1);
        Inventory inv = plugin.getServer().createInventory(null, 54, "Select a word");
        int i = -1;
        for(String word : Main.WORD_LIST){
            i++;
            ItemStack wordItem = new ItemStack(Material.BOOK);
            ItemMeta wordItemMeta = wordItem.getItemMeta();

            wordItemMeta.setDisplayName(Main.toColor("&f" + Main.toProperCase(word.replaceAll("-", " "))));
            wordItem.setItemMeta(wordItemMeta);
            inv.setItem(i, wordItem);
        }
        ItemStack backItem = new ItemStack(Material.POPPY);
        ItemMeta backItemMeta = backItem.getItemMeta();
        backItemMeta.setDisplayName(Main.toColor("&cBack"));
        backItem.setItemMeta(backItemMeta);
        inv.setItem(49, backItem);

        player.openInventory(inv);
    }
}
