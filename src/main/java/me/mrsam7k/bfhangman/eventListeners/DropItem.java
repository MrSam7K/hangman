package me.mrsam7k.bfhangman.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
}
