package me.mrsam7k.bfhangman.commands;

import me.mrsam7k.bfhangman.Hangman;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class hangmanCmd implements CommandExecutor {





    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Hangman.startGame("RANDOM", sender.getServer().getPlayer(sender.getName()));
        return true;
    }
}
