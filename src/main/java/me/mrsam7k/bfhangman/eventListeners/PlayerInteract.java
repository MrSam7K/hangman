package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.InventoryMenu;
import me.mrsam7k.bfhangman.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        String displayName = player.getItemInHand().getItemMeta().getDisplayName();
        if(player.getItemInHand().getType() == Material.IRON_DOOR && displayName.equals(Main.toColor("&eHangman Phone"))){
            InventoryMenu.modeMenu(player);
        }
    }
}
