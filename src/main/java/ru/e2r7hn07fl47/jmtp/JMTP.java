package ru.e2r7hn07fl47.jmtp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class JMTP extends JavaPlugin {

    @Override
    public void onEnable() {

        final Logger logger = this.getLogger();

        final World overworld = Bukkit.getServer().getWorlds().get(0);

        getCommand("wtp").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player)) return true;

            String nickname = sender.getName();

            if (!sender.hasPermission("minecraft.command.tp")) {
                sender.sendMessage(ChatColor.RED + "You have not enough permissions!");
                logger.warning(nickname + " tried to use teleport without permissions!");
                return true;
            }

            if (args.length < 4) {
                sender.sendMessage(ChatColor.RED + "Not enough args! Usage:");
                sender.sendMessage(ChatColor.RED + "/wtp (dim) (x) (y) (z)");
                return true;
            }

            int dim, x, y, z;
            dim = Integer.parseInt(args[0]);
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
            z = Integer.parseInt(args[3]);

            Player player = Bukkit.getPlayer(nickname);
            World world;
            if (dim == 0) {
                world = overworld;
            } else {
                try {
                    world = Bukkit.getServer().getWorld("DIM" + dim);
                    if (world == null) throw new NullPointerException();
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "No such dimension!");
                    return true;
                }
            }

            try {
                Location location = new Location(world, x, y, z);
                if (!player.getWorld().equals(world)) {
                    // There is some issues with teleporting through dimensions to exact coordinates
                    player.teleport(world.getSpawnLocation());
                }
                player.teleport(location);
                logger.warning(nickname + " has been teleported to dimension " + world.getName() + " to coordinates"
                        + " x=" + x + " y=" + y + " z=" + z);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Error while teleporting!");
                e.printStackTrace();
                return true;
            }


            return true;
        });

        getCommand("wtp_version").setExecutor((sender, command, label, args) -> {
            sender.sendMessage(ChatColor.DARK_GREEN + "JourneyMapTP (JMTP) version " + this.getDescription().getVersion());
            return true;
        });

    }

    @Override
    public void onDisable() {
    }
}
