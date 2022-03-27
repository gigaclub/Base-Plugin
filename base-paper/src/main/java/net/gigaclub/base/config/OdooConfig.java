package net.gigaclub.base.config;

import org.bukkit.configuration.file.FileConfiguration;

public class OdooConfig {

    public static void setOdooConfig() {

        FileConfiguration config = Config.getConfig();

        config.addDefault("Odoo.Host", "http://localhost:14069");
        config.addDefault("Odoo.Database", "devel");
        config.addDefault("Odoo.Username", "admin");
        config.addDefault("Odoo.Password", "admin");

        Config.save();

    }

}
