package ru.e2r7hn07fl47.jmtp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class JMTP extends JavaPlugin {

    private List<String> getWorlds() {
        List<World> worlds = Bukkit.getWorlds();
        List<String> worldList = new ArrayList<>();
        for (World world : worlds) {
            worldList.add(world.getName());
        }
        return worldList;
    }

    private boolean checkIsForgeTypeDims(List<String> allWorlds) {
        boolean isForgeDims = false;
        for (String world : allWorlds) {
            if (world.contains("DIM")) {
                isForgeDims = true;
                break;
            }
        }
        return isForgeDims;
    }

    @Override
    public void onEnable() {
        final Logger logger = this.getLogger();
        final List<String> allWorlds = getWorlds();

        final boolean isForgeDims = checkIsForgeTypeDims(allWorlds);

        final World overworld = Bukkit.getWorld("world");
        // Last two uses only in "vanilla", in "forge" they should be null and calling them could cause NPE
        final World nether = Bukkit.getWorld("world_nether");
        final World the_end = Bukkit.getWorld("world_the_end");


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

            String dim;
            double x, y, z;
            dim = args[0];
            x = Double.parseDouble(args[1]);
            y = Double.parseDouble(args[2]);
            z = Double.parseDouble(args[3]);

            Player player = Bukkit.getPlayer(nickname);
            World world = null;
            if (dim.equals("0") || dim.equals("minecraft:overworld") || dim.equals("world")) {
                world = overworld;
            } else {
                try {
                    if (isForgeDims) {
                        // If forge-type dimensions, they should be "DIM{dim}"'
                        if (!dim.startsWith("DIM")) {
                            dim = "DIM" + dim;
                        }
                        world = Bukkit.getServer().getWorld(dim);
                    }
                    else {
                        // This is fix for old MC versions
                        if (dim.equals("-1") || dim.equals("minecraft:the_nether") || dim.equals("world_nether"))
                            world = nether;
                        else if (dim.equals("1") || dim.equals("minecraft:the_end") || dim.equals("world_the_end"))
                            world = the_end;
                        else {
                            if (dim.contains(":")) dim = dim.replace(":", "_");
                            world = Bukkit.getWorld(dim);
                        }
                    }
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


        getCommand("wtp_dims_list").setExecutor((sender, command, label, args) -> {
            StringBuilder sb = new StringBuilder();
            for (String world : allWorlds) {
                sb.append('\n');
                sb.append(world);
            }
            sender.sendMessage(ChatColor.DARK_GREEN + "Here is all dimensions:" + sb);
            return true;
        });
    }

    @Override
    public void onDisable() {
    }
}
