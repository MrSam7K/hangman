package me.mrsam7k.bfhangman.eventListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TemplateListener implements Listener {

    @EventHandler
    public void CHANGETHIS(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        //woah code here yes
    }
}
