package me.dovide.stafflist.config;

import me.dovide.stafflist.utils.ColorUtil;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig extends YamlConfiguration {

    @Override
    public String getString(String text) {
        return ColorUtil.colorize(super.getString(text));
    }
}
