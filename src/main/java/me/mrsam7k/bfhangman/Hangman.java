package me.mrsam7k.bfhangman;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Hangman {
    public static String messageLine() {
        return "\u25AC".repeat(64);
    }

    public static void globalMessage(String msg) {
        String[] messages = {"&a" + Hangman.messageLine(), "&f&lHangman", "&x&B&2&3&9&2&F" + "\u2764".repeat(Main.HEARTS), "", "&x&f&f&d&9&6&6" + msg, "&eIt's &6" + Hangman.handleGuesser(Main.CURRENT_GUESSER.getName()) + "&e turn to guess.", "", "&e" + Hangman.decodeWord(), "&a" + Hangman.messageLine()};
        for (Player p : Bukkit.getOnlinePlayers()) {
            Main.sendCenteredMessage(p, messages);
        }

    }

    public static String handleGuesser(String s) {
        if (s.endsWith("s") || s.endsWith("S")) {
            return s + "'";
        } else
            return s + "'s";
    }

    public static String decodeWord() {
        String word = Main.WORD;
        ArrayList<String> decodedLetters = new ArrayList<>();
        String[] wordLetters = word.split("");

        for (String letter : wordLetters) {
            if (Main.GUESSED_LETTERS.contains(letter)) {
                decodedLetters.add(letter);
            } else {
                if (letter.equals("-")) decodedLetters.add(" ");
                else decodedLetters.add("_");
            }
        }
        return String.join("", decodedLetters);
    }

    public static void nextGuesser(boolean newGame) {
        if (newGame) {
            Main.CURRENT_GUESSER = Main.PLAYERS[0];
            Main.CURRENT_GUESSER_INT = 0;
        } else {
            Main.CURRENT_GUESSER_INT++;
            if (Main.CURRENT_GUESSER_INT >= Main.PLAYERS.length) {
                Main.CURRENT_GUESSER_INT = 0;
            }
            Main.CURRENT_GUESSER = Main.PLAYERS[Main.CURRENT_GUESSER_INT];
        }
    }

    public static void finishGame(boolean win) {
        String[] messages;
        if (!win) {

            messages = new String[]{"&c" + Hangman.messageLine(), "&f&lHangman", "", "&eWord was: " + Main.toProperCase(Main.WORD.replaceAll("-", " ")), "&c" + Hangman.messageLine()};
        } else {
            messages = new String[]{"&6" + Hangman.messageLine(), "&f&lHangman", "", "&eWord was: " + Main.toProperCase(Main.WORD.replaceAll("-", " ")), "", "&6" + Hangman.messageLine()};
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            Main.sendCenteredMessage(p, messages);
            if (win) {
                p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2, 1);
            } else p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 2, 0);
        }
        Main.ONGOING = false;
    }

    public static void startGame(String word, Player starter) {
        if (Bukkit.getOnlinePlayers().size() > 0) {
            if (!Main.ONGOING) {
                Main.HEARTS = 10;
                Main.ONGOING = true;
                Main.GUESSED_LETTERS = new ArrayList<>();
                Main.PLAYERS = Bukkit.getOnlinePlayers().toArray(new Player[0]);
                if (word.equals("RANDOM")) {
                    Main.WORD = Main.WORD_LIST[(int) (Math.random() * (Main.WORD_LIST.length - 1 + 1))];
                    starter.sendMessage(Main.WORD);
                } else {
                    Main.WORD = word;
                }
                Hangman.nextGuesser(true);
                Hangman.globalMessage("Good luck!");
            } else {
                Main.actionBar("&cGame already started.", starter);
                starter.closeInventory();
            }
        } else {
            starter.closeInventory();
            Main.actionBar("&c2+ players need to be online to start hangman game.", starter);
        }
    }

    public static boolean guess(Player player, String word) {
        String playerName = player.getName();

        if (Main.ONGOING) {
            if (playerName.equals(Main.CURRENT_GUESSER.getName())) {
                if (Main.WORD.contains(word)) {
                    if (Main.GUESSED_LETTERS.contains(word)) {
                        Main.actionBar("&cThis letter was already guessed!", player);
                    } else {
                        Main.GUESSED_LETTERS.addAll(Arrays.asList(word.split("")));
                        if (!Hangman.decodeWord().contains("_")) {
                            Hangman.finishGame(true);
                        } else {
                            Hangman.globalMessage(Hangman.handleGuesser(player.getName()) + " guess: " + word);
                        }
                    }
                } else {
                    Main.actionBar("&cWrong guess, better luck next time!", player);
                    Main.HEARTS--;
                    if (Main.HEARTS <= 0) {
                        Hangman.finishGame(false);
                    } else {
                        Hangman.nextGuesser(false);
                        Hangman.globalMessage("&m" + Hangman.handleGuesser(player.getName()) + " guess: " + word);
                    }
                }
                return true;
            }
        }
        return false;
    }

}
