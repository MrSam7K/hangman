package me.mrsam7k.bfhangman.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SwapHands implements Listener {

    @EventHandler
    public void swapHands(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }
}
