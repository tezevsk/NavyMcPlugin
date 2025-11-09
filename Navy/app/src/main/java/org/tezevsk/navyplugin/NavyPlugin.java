package org.tezevsk.navyplugin;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

//import java.io.Console;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NavyPlugin extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    Bukkit.getServer().getLogger().info("Navy Navigation plugin enabled.©tezevsk v"+getDescription().getVersion()+" " + getDescription().getWebsite());

    saveResource("config.yml", false);
    saveDefaultConfig();


  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!\nThis server uses §6§l[Navy] Navigation systems\n§r/navy help\nFor more info\n---\n§6©tezevsk 2025. Thanks For Using"));
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      
    if (command.getName().equalsIgnoreCase("navy")) {
        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("List of commands:\n Commands inherent to navy\n  /navy help §6This page\n  §r/navy list §6List avaible worlds\n  §r/navy <dest> §6Teleport to location\n");

            return true;
        }
        if (sender instanceof Player p) {
            // /execute ...

            String worldOrigin = getConfig().getString("Overrides."+args[0]);

            World targetWorld = Bukkit.getServer().getWorld(worldOrigin);
            if (targetWorld == null) {
                // Handle the case where the world doesn't exist or isn't loaded
                p.sendMessage(ChatColor.RED + "The world or command '"+args[0]+"' could not be found or loaded.");
                return true;
            }

            double x = getConfig().getDouble("Spawn."+worldOrigin+".x");
            double y = getConfig().getDouble("Spawn."+worldOrigin+".y");
            double z = getConfig().getDouble("Spawn."+worldOrigin+".z");

            Location destination = new Location(targetWorld, x, y, z, getConfig().getInt("Spawn."+worldOrigin+".yaw"), getConfig().getInt("Spawn."+worldOrigin+".pitch"));
            p.teleport(destination);

            p.sendMessage(ChatColor.BLUE + "You're now in "+args[0]+"!");

            return true;
        }
        else {
            Bukkit.getServer().getLogger().warning("Can be only executed by player");

            return false;
        }

    }
    if (command.getName().equalsIgnoreCase("navylobby")) {
      if (sender instanceof Player) {
        Player p = (Player) sender;
        Location dest = new Location(Bukkit.getServer().getWorld("world"), 0, 0, 0, 0, 0);
        p.teleport(dest);
        p.sendMessage(ChatColor.BLUE + "You're now in Lobby!");
      }
    }
    if (command.getName().equalsIgnoreCase("navytest")) {
      if (sender instanceof Player) {
        Player p = (Player) sender;
        p.sendMessage(ChatColor.BLUE + "Navy test executed! Now try running /navy list.");
      }
    }

    return true;
  }

  @Override
  public void onDisable() {
    Bukkit.getServer().getLogger().info("[Navy] Navigation plugin disabled\n§6Thank's for using!");
  }
}