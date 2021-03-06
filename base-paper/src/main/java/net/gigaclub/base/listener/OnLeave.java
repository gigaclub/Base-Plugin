package net.gigaclub.base.listener;

import net.gigaclub.base.Base;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String playerUUID = player.getUniqueId().toString();
        Base.getData().updateStatus(playerUUID, "offline");
    }

}
