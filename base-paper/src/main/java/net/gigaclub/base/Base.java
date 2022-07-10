package net.gigaclub.base;

import net.gigaclub.base.config.Config;
import net.gigaclub.base.config.OdooConfig;
import net.gigaclub.base.data.Data;
import net.gigaclub.base.listener.OnJoin;
import net.gigaclub.base.listener.OnLeave;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Base extends JavaPlugin {

    private static Base plugin;
    private static Data data;

    public static Base getPlugin() {
        return plugin;
    }

    private static void setPlugin(Base plugin) {
        Base.plugin = plugin;
    }

    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        Base.data = data;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        setConfig();

        File file = new File("plugins//" + "Odoo", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        setData(new Data(
                config.getString("Odoo.Host"),
                config.getString("Odoo.Database"),
                config.getString("Odoo.Username"),
                config.getString("Odoo.Password")
        ));
        registerEvents();
    }

    @Override
    public void onDisable() {
        for (Player p : this.getServer().getOnlinePlayers()) {
            Base.getData().updateStatus(p.getUniqueId().toString(), "offline");
        }
    }

    private void setConfig() {
        Config.createConfig();
        OdooConfig.setOdooConfig();
        Config.save();
        getLogger().info("Config files set.");
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new OnJoin(), getPlugin());
        Bukkit.getPluginManager().registerEvents(new OnLeave(), getPlugin());
    }
}
