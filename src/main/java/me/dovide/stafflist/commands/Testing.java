package me.dovide.stafflist.commands;

import me.dovide.stafflist.ListMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Testing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(ListMain.getPermissions().getPrimaryGroup(player));
            return true;
        }
        if(args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            player.sendMessage(ListMain.getPermissions().getPrimaryGroup(target));
            if(Arrays.toString(ListMain.getPermissions().getPlayerGroups(target)).contains("supporter"))
                player.sendMessage("true");
            else
                player.sendMessage("false");
            return true;
        }

        return true;
    }
}
