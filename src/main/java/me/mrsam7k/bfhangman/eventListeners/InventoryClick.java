package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.Hangman;
import me.mrsam7k.bfhangman.InventoryMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);
        ItemStack item = e.getCurrentItem();
        String title = e.getView().getTitle();
        if (!(e.getWhoClicked() instanceof Player player)) return;
        if (title.equals("Select mode")) {
            if (item.getType() == Material.IRON_NUGGET) {
                InventoryMenu.chooseWord(player);
            } else if (item.getType() == Material.PRISMARINE_CRYSTALS) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2, 1);
                Hangman.startGame("RANDOM", player);
                player.closeInventory();
            }
        } else if (title.equals("Select a word")) {
            if (item.getType() == Material.BOOK) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2, 1);
                Hangman.startGame(ChatColor.stripColor(item.getItemMeta().getDisplayName()).replaceAll(" ", "-").toLowerCase(), player);
                player.closeInventory();
            } else if (item.getType() == Material.POPPY) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2, 1);
                InventoryMenu.modeMenu(player);
            }
        }
    }
}
