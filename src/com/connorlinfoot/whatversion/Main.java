package com.connorlinfoot.whatversion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
    private static Plugin instance;
    public static String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "WhatVersion" + ChatColor.GRAY + "] " + ChatColor.WHITE;
    public static boolean opMessage = false;

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
        console.sendMessage(ChatColor.RED + "This plugin does NOT block 1.7 OR 1.8 clients connecting");
        console.sendMessage(ChatColor.RED + "Please use protocol management if you wish to do this");
        console.sendMessage(ChatColor.RED + "http://spigotmc.org/resources/protocolmanagement.1317/");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!opMessage && event.getPlayer().isOp()) {
            opMessage = true;
            event.getPlayer().sendMessage(ChatColor.AQUA + "You are running WhatVersion By Connor Linfoot");
            event.getPlayer().sendMessage(ChatColor.RED + "This plugin does NOT block 1.7 OR 1.8 clients connecting");
            event.getPlayer().sendMessage(ChatColor.RED + "Please use protocol management if you wish to do this - http://spigotmc.org/resources/protocolmanagement.1317/");
            event.getPlayer().sendMessage(ChatColor.RED + "This message is shown to the first OP player who joins after a reload or reboot/startup");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /pv or /wv

        if (args.length == 2 && args[0].equalsIgnoreCase("kick")) {
            if (args[1].equalsIgnoreCase("1.7")) {
                if (sender.hasPermission("whatversion.kick.1.7")) {
                    sender.sendMessage(prefix + "Players running on 1.7.x:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 5 || version == 4) {
                            sender.sendMessage("Kicked: " + p.getDisplayName());
                            p.kickPlayer("You was kicked");
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.kick.1.7\"");
                }
            } else if (args[1].equalsIgnoreCase("1.7.1") || args[1].equalsIgnoreCase("1.7.2") || args[1].equalsIgnoreCase("1.7.3") || args[1].equalsIgnoreCase("1.7.4") || args[1].equalsIgnoreCase("1.7.5")) {
                if (sender.hasPermission("whatversion.kick.1.7")) {
                    sender.sendMessage(prefix + "Players running on 1.7.1 - 1.7.5:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 4) {
                            sender.sendMessage("Kicked: " + p.getDisplayName());
                            p.kickPlayer("You was kicked");
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.kick.1.7\"");
                }
            } else if (args[1].equalsIgnoreCase("1.7.6") || args[1].equalsIgnoreCase("1.7.7") || args[1].equalsIgnoreCase("1.7.8") || args[1].equalsIgnoreCase("1.7.9") || args[1].equalsIgnoreCase("1.7.10")) {
                if (sender.hasPermission("whatversion.kick.1.7")) {
                    sender.sendMessage(prefix + "Players running on 1.7.6 - 1.7.10:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 5) {
                            sender.sendMessage("Kicked: " + p.getDisplayName());
                            p.kickPlayer("You was kicked");
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.kick.1.7\"");
                }
            } else if (args[0].equalsIgnoreCase("1.8")) {
                if (sender.hasPermission("whatversion.kick.1.8")) {
                    sender.sendMessage(prefix + "Players running on 1.8:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 47) {
                            sender.sendMessage("Kicked: " + p.getDisplayName());
                            p.kickPlayer("You was kicked");
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.kick.1.8\"");
                }
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("1.7")) {
                if (sender.hasPermission("whatversion.1.7")) {
                    sender.sendMessage(prefix + "Players running on 1.7.x:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 5 || version == 4) {
                            sender.sendMessage(p.getDisplayName());
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.1.7\"");
                }
            } else if (args[0].equalsIgnoreCase("1.7.1") || args[0].equalsIgnoreCase("1.7.2") || args[0].equalsIgnoreCase("1.7.3") || args[0].equalsIgnoreCase("1.7.4") || args[0].equalsIgnoreCase("1.7.5")) {
                if (sender.hasPermission("whatversion.1.7")) {
                    sender.sendMessage(prefix + "Players running on 1.7.1 - 1.7.5:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 4) {
                            sender.sendMessage(p.getDisplayName());
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.1.7\"");
                }
            } else if (args[0].equalsIgnoreCase("1.7.6") || args[0].equalsIgnoreCase("1.7.7") || args[0].equalsIgnoreCase("1.7.8") || args[0].equalsIgnoreCase("1.7.9") || args[0].equalsIgnoreCase("1.7.10")) {
                if (sender.hasPermission("whatversion.1.7")) {
                    sender.sendMessage(prefix + "Players running on 1.7.6 - 1.7.10:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 5) {
                            sender.sendMessage(p.getDisplayName());
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.1.7\"");
                }
            } else if (args[0].equalsIgnoreCase("1.8")) {
                if (sender.hasPermission("whatversion.1.8")) {
                    sender.sendMessage(prefix + "Players running on 1.8:");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftPlayer = (CraftPlayer) p;
                        Integer version = craftPlayer.getHandle().playerConnection.networkManager.getVersion();
                        if (version == 47) {
                            sender.sendMessage(p.getDisplayName());
                        }
                    }
                    sender.sendMessage(prefix + "Done");
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.1.8\"");
                }
            } else {
                if (sender.hasPermission("whatversion.player")) {
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
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You are missing the permission \"whatversion.player\"");
                }
            }
        } else {
            sender.sendMessage(prefix + "WhatVersion By Connor Linfoot");
            if (sender.hasPermission("whatversion.player"))
                sender.sendMessage(prefix + "/pv <player> - Get a players Minecraft Version");
            if (sender.hasPermission("whatversion.1.7"))
                sender.sendMessage(prefix + "/pv 1.7 - List all players on 1.7");
            if (sender.hasPermission("whatversion.1.7"))
                sender.sendMessage(prefix + "/pv 1.7.(x) - List all players on 1.7.(x)");
            if (sender.hasPermission("whatversion.1.8"))
                sender.sendMessage(prefix + "/pv 1.8 - List all players on 1.8");
            if (sender.hasPermission("whatversion.kick.1.7"))
                sender.sendMessage(prefix + "/pv kick 1.7 - Kick all players on 1.7");
            if (sender.hasPermission("whatversion.kick.1.7"))
                sender.sendMessage(prefix + "/pv kick 1.7.(x) - Kick all players on 1.7.(x)");
            if (sender.hasPermission("whatversion.kick.1.8"))
                sender.sendMessage(prefix + "/pv kick 1.8 - Kick all players on 1.8");
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
