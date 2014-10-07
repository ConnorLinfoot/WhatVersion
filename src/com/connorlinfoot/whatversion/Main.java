package com.connorlinfoot.whatversion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
    private static Plugin instance;
    public static String prefix = ChatColor.AQUA + "[PV] " + ChatColor.WHITE;

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");
        console.sendMessage(ChatColor.AQUA + getDescription().getName());
        console.sendMessage(ChatColor.AQUA + "Version " + getDescription().getVersion());
        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /pv or /wv

        if( args.length == 1 ){
            if( args[0].equalsIgnoreCase("1.7") ){
                sender.sendMessage(prefix + "Players running on 1.7.x:");
                for( Player p : Bukkit.getOnlinePlayers() ){
                    CraftPlayer craftPlayer = (CraftPlayer) p;
                    Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                    if (version == 5 || version == 4) {
                        sender.sendMessage(p.getDisplayName());
                    }
                }
                sender.sendMessage(prefix + "Done");
            } else if( args[0].equalsIgnoreCase("1.8") ){
                sender.sendMessage(prefix + "Players running on 1.8:");
                for( Player p : Bukkit.getOnlinePlayers() ){
                    CraftPlayer craftPlayer = (CraftPlayer) p;
                    Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                    if (version == 47) {
                        sender.sendMessage(p.getDisplayName());
                    }
                }
                sender.sendMessage(prefix + "Done");
            } else {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(prefix + ChatColor.RED + "Player not found");
                    return false;
                }

                Player player = Bukkit.getPlayer(args[0]);
                CraftPlayer craftPlayer = (CraftPlayer) player;
                Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                String versionF = "";
                if (version == 47) {
                    versionF = "1.8";
                } else if (version == 5) {
                    versionF = "1.7.6 - 1.7.10";
                } else if (version == 4) {
                    versionF = "1.7.1 - 1.7.5";
                } else {
                    versionF = "Unknown (" + version + ")";
                }

                sender.sendMessage(prefix + player.getDisplayName() + " is running on Minecraft Version " + versionF);
            }
        } else {
            sender.sendMessage(prefix + "WhatVersion");
            sender.sendMessage(prefix + "/pv <player> - Get a players Minecraft Version");
            sender.sendMessage(prefix + "/pv 1.7 - List all players on 1.7");
            sender.sendMessage(prefix + "/pv 1.8 - List all players on 1.8");
        }
        return false;
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
