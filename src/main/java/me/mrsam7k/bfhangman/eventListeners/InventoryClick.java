package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.Hangman;
import me.mrsam7k.bfhangman.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);
        ItemStack item = e.getCurrentItem();
        Inventory menu = e.getClickedInventory();
        String title = e.getView().getTitle();
        if(title.equals("Select mode")){
            if(item.getType() == Material.IRON_NUGGET) {
                InventoryMenu.chooseWord(e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()));
            } else if(item.getType() == Material.PRISMARINE_CRYSTALS){
                e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()).playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 2 ,1);
                Hangman.startGame("RANDOM", e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()));
                e.getWhoClicked().closeInventory();
            }
        } else if(title.equals("Select a word")) {
                if(item.getType() == Material.BOOK){
                    e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()).playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 2 ,1);
                    String word = item.getItemMeta().getDisplayName().replaceAll(" ", "-").toLowerCase();
                    Hangman.startGame(word, e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()));
                    e.getWhoClicked().closeInventory();
                } else if(item.getType() == Material.POPPY){
                    e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()).playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 2 ,1);
                    InventoryMenu.modeMenu(e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()));
                }
        }
    }
}
