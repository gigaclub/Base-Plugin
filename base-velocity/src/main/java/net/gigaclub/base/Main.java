package net.gigaclub.base;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "base-velocity",
        name = "Base",
        version = "@version@",
        description = "A Base Plugin for Minecraft",
        url = "https://GigaClub.net",
        authors = {"KevTVKevin"}
)
public class Main {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
