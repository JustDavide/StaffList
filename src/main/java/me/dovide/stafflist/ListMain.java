package me.dovide.stafflist;

import me.dovide.stafflist.commands.ListCommand;
import me.dovide.stafflist.commands.SlAdmin;
import me.dovide.stafflist.commands.Testing;
import me.dovide.stafflist.config.CustomConfig;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class ListMain extends JavaPlugin {

    private static ListMain plugin;
    private CustomConfig config;
    private static Permission perms = null;

    @Override
    public void onEnable() {

        // Instance
        plugin = this;

        // Enable Mex
        getLogger().info("StaffList Abilitata con successo");


        // Config
        reloadCustomConfig();

        // Commands
        getCommand("stafflist").setExecutor(new ListCommand());
        getCommand("stafflistadmin").setExecutor(new SlAdmin());
//        getCommand("testdovide").setExecutor(new Testing());

        // Perms
        setupPermissions();

    }

    @Override
    public void onDisable() {
        getLogger().info("StaffList Disabilitata con successo");
    }

    public static ListMain getInstance() {
        return plugin;
    }

    public CustomConfig getConfig(){
        return config;
    }

    public CustomConfig createConfig(String name) {
        File fc = new File(getDataFolder(), name);
        if(!fc.exists()){
            fc.getParentFile().mkdir();
            saveResource(name, false);
        }
        CustomConfig config = new CustomConfig();
        try {
            config.load(fc);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        return config;
    }

    public void reloadCustomConfig() {
        config = createConfig("config.yml");
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Permission getPermissions() {
        return perms;
    }
}
