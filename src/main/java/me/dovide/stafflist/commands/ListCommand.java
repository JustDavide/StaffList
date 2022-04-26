package me.dovide.stafflist.commands;

import me.dovide.stafflist.ListMain;
import me.dovide.stafflist.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))
            return true;

        Plugin plugin = ListMain.getInstance();

        Player player = (Player) sender;

        List<String> founderList = new ArrayList<>(),
                gestoreList = new ArrayList<>(),
                developerList = new ArrayList<>(),
                pluginnerList = new ArrayList<>(),
                builderList = new ArrayList<>(),
                modList = new ArrayList<>(),
                supporterList = new ArrayList<>(),
                adminList = new ArrayList<>();

        String founderPerm = plugin.getConfig().getString("group.founder"),
                gestorePerm = plugin.getConfig().getString("group.gestore"),
                adminPerm = plugin.getConfig().getString("group.admin"),
                developerPerm = plugin.getConfig().getString("group.developer"),
                pluginnerPerm = plugin.getConfig().getString("group.pluginner"),
                builderPerm = plugin.getConfig().getString("group.builder"),
                modPerm = plugin.getConfig().getString("group.mod"),
                supporterPerm = plugin.getConfig().getString("group.supporter"),
                usage = plugin.getConfig().getString("list.usage");

        if (args.length != 0) {
            sender.sendMessage(usage);
            return true;
        }


        for (Player allPlayers : Bukkit.getOnlinePlayers()) {

            String playerGroup = ListMain.getPermissions().getPrimaryGroup(allPlayers);
            String specialGroups = Arrays.toString(ListMain.getPermissions().getPlayerGroups(allPlayers));

            if (playerGroup.equalsIgnoreCase(founderPerm))
                founderList.add(allPlayers.getName());

            if (playerGroup.equalsIgnoreCase(pluginnerPerm))
                pluginnerList.add(allPlayers.getName());

            if (playerGroup.equalsIgnoreCase(adminPerm))
                adminList.add(allPlayers.getName());

            if (playerGroup.equalsIgnoreCase(developerPerm))
                developerList.add(allPlayers.getName());

            if (playerGroup.equalsIgnoreCase(gestorePerm))
                gestoreList.add(allPlayers.getName());

            if (playerGroup.equalsIgnoreCase(builderPerm))
                builderList.add(allPlayers.getName());

            if (specialGroups.contains(modPerm))
                modList.add(allPlayers.getName());

            if(specialGroups.contains(supporterPerm))
                supporterList.add(allPlayers.getName());

        }

        if (!(founderList.isEmpty() && pluginnerList.isEmpty() && gestoreList.isEmpty() && developerList.isEmpty() && builderList.isEmpty() && supporterList.isEmpty() && adminList.isEmpty())) {

            String founderMex = plugin.getConfig().getString("list.founder").replace("%listPlayers%", String.join(", ", founderList)),
                    gestoreMex = plugin.getConfig().getString("list.gestore").replace("%listPlayers%", String.join(", ", gestoreList)),
                    adminMex = plugin.getConfig().getString("list.admin").replace("%listPlayers%", String.join(", ", adminList)),
                    developerMex = plugin.getConfig().getString("list.developer").replace("%listPlayers%", String.join(", ", developerList)),
                    pluginnerMex = plugin.getConfig().getString("list.pluginner").replace("%listPlayers%", String.join(", ", pluginnerList)),
                    builderMex = plugin.getConfig().getString("list.builder").replace("%listPlayers%", String.join(", ", builderList)),
                    modMex = plugin.getConfig().getString("list.mod").replace("%listPlayers%", String.join(", ", modList)),
                    supporterMex = plugin.getConfig().getString("list.supporter").replace("%listPlayers%", String.join(", ", supporterList)),
                    startMex = plugin.getConfig().getString("list.start-mex");

            player.sendMessage(startMex);
            player.sendMessage("");

            if (!founderList.isEmpty())
                player.sendMessage(founderMex);


            if (!adminList.isEmpty())
                player.sendMessage(adminMex);

            if (!(gestoreList.isEmpty()))
                player.sendMessage(gestoreMex);


            if (!(developerList.isEmpty()))
                player.sendMessage(developerMex);


            if (!(pluginnerList.isEmpty()))
                player.sendMessage(pluginnerMex);


            if (!(modList.isEmpty()))
                player.sendMessage(modMex);


            if (!(supporterList.isEmpty()))
                player.sendMessage(supporterMex);


            if (!(builderList.isEmpty()))
                player.sendMessage(builderMex);

            player.sendMessage("");

        } else
            player.sendMessage(ColorUtil.colorize("&cNessuno staff è online"));

        return true;
    }
}
