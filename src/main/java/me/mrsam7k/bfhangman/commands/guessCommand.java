package me.mrsam7k.bfhangman.commands;

import me.mrsam7k.bfhangman.Hangman;
import me.mrsam7k.bfhangman.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class guessCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == -1) {
            sender.sendMessage(Main.toColor("&4&l\u00BB &cPlease enter letter to guess!"));
            return false;
        }
        String playerName = sender.getName();
        Hangman.guess(sender.getServer().getPlayer(sender.getName()), args[0]);
        return true;
    }
}
