package me.mrsam7k.bfhangman.commands;

import me.mrsam7k.bfhangman.Hangman;
import me.mrsam7k.bfhangman.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuessCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Hangman.guess(player, args[0]);
        } else {
            sender.sendMessage(Main.toColor("&cYou must be a player to do this!"));
        }
        return true;
    }
}
