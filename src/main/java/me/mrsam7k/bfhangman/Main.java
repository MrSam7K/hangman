package me.mrsam7k.bfhangman;

import me.mrsam7k.bfhangman.commands.guessCommand;
import me.mrsam7k.bfhangman.commands.hangmanCmd;
import me.mrsam7k.bfhangman.eventListeners.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPlaceBlock(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new DropItem(), this);
        Bukkit.getPluginManager().registerEvents(new SwapHands(), this);
        getCommand("guess").setExecutor((new guessCommand()));
        getCommand("hangman").setExecutor((new hangmanCmd()));
        Hangman hangman = new Hangman();
        Main.ONGOING = false;
        Main.WORD_LIST = new String[]{"creeper", "biome", "enderman", "ender-dragon", "the-end", "bedrock", "nether-portal", "redstone", "mob", "zombie", "wither-skeleton", "bunny-farm", "ultra-is-epic"};

    }

    public static boolean ONGOING;
    public static int HEARTS;
    public static Player CURRENT_GUESSER;
    public static int CURRENT_GUESSER_INT;
    public static String WORD;
    public static ArrayList<String> GUESSED_LETTERS;
    public static Object[] PLAYERS;
    public static String[] WORD_LIST;


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void actionBar(String msg, Player p){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Main.toColor(msg)));
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 2 ,1);
    }
    public static String toColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String toProperCase(String s) {
        String[] s2 = s.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String word : s2){
            list.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
        }
        return String.join(" ", list);
    }

    private final static int CENTER_PX = 154;

    public static void sendCenteredMessage(Player player, String[] messages) {
        for (String message : messages) {
            if (message == null || message.equals("")) player.sendMessage("");
            message = ChatColor.translateAlternateColorCodes('&', message);

            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;

            for (char c : message.toCharArray()) {
                if (c == '\u00A7') {
                    previousCode = true;
                    continue;
                } else if (previousCode == true) {
                    previousCode = false;
                    if (c == 'l' || c == 'L') {
                        isBold = true;
                        continue;
                    } else isBold = false;
                } else {
                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                    messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                    messagePxSize++;
                }
            }

            int halvedMessageSize = messagePxSize / 2;
            int toCompensate = CENTER_PX - halvedMessageSize;
            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while (compensated < toCompensate) {
                sb.append(" ");
                compensated += spaceLength;
            }
            player.sendMessage(sb + message);
        }

    }
}


