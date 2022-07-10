package net.gigaclub.base.listener;

import net.gigaclub.base.Base;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();
        if (Base.getData().checkIfPlayerExists(playerUUID)) {
            if (Base.getData().checkName(playerName, playerUUID)) {
                Base.getData().updateName(playerName, playerUUID);
            }
        } else {
            Base.getData().createPlayer(playerName, playerUUID);
        }
        Base.getData().updateStatus(playerUUID, "online");
    }

}
