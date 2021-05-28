package net.gigaclub.base;

import net.gigaclub.base.config.Config;
import net.gigaclub.base.config.OdooConfig;
import net.gigaclub.base.data.Odoo;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;
    final public static String PREFIX = "[GC]: ";

    @Override
    public void onEnable() {
        // Plugin startup logic

        setPlugin(this);
        setConfig();
        FileConfiguration config = getConfig();
        Odoo odoo = new Odoo(
                config.getString("Base.Odoo.Host"),
                config.getString("Base.Odoo.Database"),
                config.getString("Base.Odoo.Username"),
                config.getString("Base.Odoo.Password")
        );

        System.out.println(odoo.getDemo());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

    private static void setPlugin(Main plugin) {
        Main.plugin = plugin;
    }

    private void setConfig() {
        Config.createConfig();

        OdooConfig.setOdooConfig();

        Config.save();

        System.out.println(PREFIX + "Config files set.");
    }

}
