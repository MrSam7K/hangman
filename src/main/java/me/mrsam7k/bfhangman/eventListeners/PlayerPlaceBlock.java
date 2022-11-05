package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerPlaceBlock implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if(!e.getPlayer().isOp()){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.toColor("&cYou are not allowed to place blocks!"));
        }
        String displayName = player.getItemInHand().getItemMeta().getDisplayName();
        if(player.getItemInHand().getType() == Material.IRON_DOOR && displayName.equals(Main.toColor("&eHangman Phone"))){
            e.setCancelled(true);
        }
    }
}
