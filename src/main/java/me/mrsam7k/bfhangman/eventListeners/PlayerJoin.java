package me.mrsam7k.bfhangman.eventListeners;

import me.mrsam7k.bfhangman.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        ItemStack itemPhone = new ItemStack(Material.IRON_DOOR, 1);

        ItemMeta itemPhoneMeta = itemPhone.getItemMeta();
        itemPhoneMeta.setDisplayName(Main.toColor("&eHangman Phone"));
        itemPhone.setItemMeta(itemPhoneMeta);


        Inventory inv = player.getInventory();
        inv.setItem(4, itemPhone);
    }
}
