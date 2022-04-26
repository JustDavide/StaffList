package me.dovide.stafflist.commands;

import me.dovide.stafflist.ListMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SlAdmin implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player))
            return true;

        String reload = ListMain.getInstance().getConfig().getString("admin.reload"),
                perm = ListMain.getInstance().getConfig().getString("admin.perm"),
                usage = ListMain.getInstance().getConfig().getString("admin.usage"),
                noperm = ListMain.getInstance().getConfig().getString("admin.noperm");

        Plugin plugin = ListMain.getInstance();

        Player player = (Player) sender;

        if(args.length != 1){
            player.sendMessage(usage);
            return true;
        }

        if(player.hasPermission(perm)) {
            if (args[0].equalsIgnoreCase("reload")) {
                ListMain.getInstance().reloadCustomConfig();
                player.sendMessage(reload);
            }
        }else{
            player.sendMessage(noperm);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1){
            List<String> firstArg = new ArrayList<>();
            firstArg.add("reload");
            return firstArg;
        }
        return null;
    }
}
