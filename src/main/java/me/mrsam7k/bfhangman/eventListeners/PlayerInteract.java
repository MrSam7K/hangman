package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.InventoryMenu;
import me.mrsam7k.bfhangman.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemMeta meta = player.getItemInHand().getItemMeta();
        if (meta == null) return;
        if (player.getItemInHand().getType() == Material.IRON_DOOR && meta.getDisplayName().equals(Main.toColor("&eHangman Phone"))) {
            InventoryMenu.modeMenu(player);
        }
    }
}
