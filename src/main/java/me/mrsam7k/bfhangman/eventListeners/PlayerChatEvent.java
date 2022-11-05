package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.Hangman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage().replaceAll(" ", "-").toLowerCase();
        if(Hangman.guess(e.getPlayer(), message)){
            e.setCancelled(true);
        }
    }
}
